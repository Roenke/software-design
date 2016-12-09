package com.spbau.bibaev.software.design.messenger.server;

import com.spbau.bibaev.software.design.messenger.app.NamedUser;
import com.spbau.bibaev.software.design.messenger.app.Service;
import com.spbau.bibaev.software.design.messenger.app.UserImpl;
import com.spbau.bibaev.software.design.messenger.client.Message;
import com.spbau.bibaev.software.design.messenger.client.TextMessage;
import com.spbau.bibaev.software.design.messenger.common.AnswerCodes;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageReceiverService implements Service {
  private final ExecutorService myThreadPool;

  private final List<ReceiverListener> myListeners = new CopyOnWriteArrayList<>();
  private volatile ServerSocket myServerSocket;

  public MessageReceiverService(int handlersThreadCount) {
    myThreadPool = Executors.newFixedThreadPool(handlersThreadCount);
  }

  public void attach(int port) {
    try {
      if (myServerSocket != null) {
        myServerSocket.close();
      }
      myServerSocket = new ServerSocket(port);

      final ServerSocket socket = myServerSocket;
      new Thread(() -> {
        while (!socket.isClosed()) {
          final NamedUser user;
          final byte[] data;
          try (Socket clientSocket = socket.accept();
               DataInputStream in = new DataInputStream(clientSocket.getInputStream());
               DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

            final InetAddress address = clientSocket.getInetAddress();

            String userName = in.readUTF();
            final int clientPort = in.readInt();
            int dataLength = in.readInt();

            user = new UserImpl(userName, address, clientPort);

            data = new byte[dataLength];
            IOUtils.readFully(in, data);

            out.writeInt(AnswerCodes.OK);
          } catch (IOException ignored) {
            continue;
          }

          fireMessageReceived(new TextMessage(user, new Date(), new String(data)));
        }
      }).start();
    } catch (IOException ignored) {
    }
  }

  public void addListener(@NotNull ReceiverListener listener) {
    myListeners.add(listener);
  }

  public void removeListener(@NotNull ReceiverListener listener) {
    myListeners.remove(listener);
  }

  private void fireMessageReceived(@NotNull Message message) {
    myListeners.forEach(listener -> listener.messageReceived(message));
  }
}
