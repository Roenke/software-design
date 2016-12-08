package com.spbau.bibaev.software.design.messenger.server;

import com.spbau.bibaev.software.design.messenger.app.Service;
import com.spbau.bibaev.software.design.messenger.app.User;
import com.spbau.bibaev.software.design.messenger.client.Message;
import com.spbau.bibaev.software.design.messenger.client.MessageType;
import com.spbau.bibaev.software.design.messenger.client.TextMessage;
import com.spbau.bibaev.software.design.messenger.common.AnswerCodes;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageReceiverService implements Service {
  private final ExecutorService myThreadPool;

  private final Map<Integer, ServerSocket> myPort2OpenSocket = new ConcurrentHashMap<>();
  private final List<ReceiverListener> myListeners = new CopyOnWriteArrayList<>();

  public MessageReceiverService(int handlersThreadCount) {
    myThreadPool = Executors.newFixedThreadPool(handlersThreadCount);
  }

  public void attach(int port) {
    if (myPort2OpenSocket.containsKey(port)) {
      // TODO: throw more clear exception
      throw new RuntimeException("Already connected");
    }

    // TODO: not thread safe
    myThreadPool.execute(new MyConnectionListener(port));
  }

  public void detachAll() throws IOException {
    for (Integer port : myPort2OpenSocket.keySet()) {
      detach(port);
    }
  }

  public void detach(int port) throws IOException {
    final ServerSocket socket = myPort2OpenSocket.get(port);
    if (socket != null) {
      socket.close();
    }
  }

  public void addListener(@NotNull MessageType type, @NotNull ReceiverListener listener) {
    myListeners.add(listener);
  }

  public void removeListener(@NotNull ReceiverListener listener) {
    myListeners.remove(listener);
  }

  private class MyConnectionListener implements Runnable {
    private final int myPort;

    MyConnectionListener(int port) {
      myPort = port;
    }

    @Override
    public void run() {
      try (ServerSocket socket = new ServerSocket(myPort)) {
        myPort2OpenSocket.put(myPort, socket);
        while (!socket.isClosed()) {
          Socket clientSocket = socket.accept();
          final int clientPort = clientSocket.getPort();
          final String address = clientSocket.getInetAddress().toString();
          DataInputStream in = new DataInputStream(clientSocket.getInputStream());
          DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

          String userName = in.readUTF();
          int messageType = in.readInt();
          int dataLength = in.readInt();

          final User user = new User(userName, address, clientPort);

          byte[] data = new byte[dataLength];
          IOUtils.readFully(in, data);
          if (0 <= messageType && messageType < MessageType.values().length) {
            MessageType type = MessageType.values()[messageType];
            switch (type) {
              case TEXT:
                String text = in.readUTF();
                fireMessageReceived(new TextMessage(user, new Date(), text));
                out.writeInt(AnswerCodes.OK);
                break;
            }
          } else {
            out.writeInt(AnswerCodes.UNKNOWN_MESSAGE_TYPE);
          }
        }
      } catch (IOException e) {
        myPort2OpenSocket.remove(myPort);
        e.printStackTrace();
      }
    }

    private void fireMessageReceived(@NotNull Message message) {
      myListeners.forEach(listener -> listener.messageReceived(message));
    }
  }
}
