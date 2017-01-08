package com.spbau.bibaev.software.design.messenger.server;

import com.spbau.bibaev.software.design.messenger.app.NamedUser;
import com.spbau.bibaev.software.design.messenger.app.Service;
import com.spbau.bibaev.software.design.messenger.app.UserImpl;
import com.spbau.bibaev.software.design.messenger.client.Message;
import com.spbau.bibaev.software.design.messenger.client.MessageSendingService;
import com.spbau.bibaev.software.design.messenger.client.TextMessage;
import com.spbau.bibaev.software.design.messenger.common.AnswerCodes;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

/**
 * Represents a service for obtaining messages from other users
 *
 * @author Vitaliy.Bibaev
 */
public class MessageReceiverService implements Service {
  private static final Logger LOG = LogManager.getLogger(MessageSendingService.class);
  private final ExecutorService myThreadPool;

  private final List<ReceiverListener> myListeners = new CopyOnWriteArrayList<>();
  private volatile ServerSocket myServerSocket;

  /**
   * Constructs a new instance of {@link MessageReceiverService}
   *
   * @param handlersThreadCount A count of the threads for sending workers
   */
  public MessageReceiverService(int handlersThreadCount) {
    myThreadPool = Executors.newFixedThreadPool(handlersThreadCount);
  }

  /**
   * Start listen to new port for input connection
   *
   * @param port A 16-bit port number
   */
  public void attach(int port) {
    try {
      if (myServerSocket != null) {
        myServerSocket.close();
      }
      myServerSocket = new ServerSocket(port);

      final ServerSocket socket = myServerSocket;
      new Thread(() -> {
        while (!socket.isClosed()) {
          try {
            final Socket clientSocket = socket.accept();
            myThreadPool.execute(new MyConnectionHandler(clientSocket));
          } catch (IOException e) {
            LOG.info("socket closed");
          }
        }
      }).start();
    } catch (IOException e) {
      LOG.error("Cannot open server socket on port " + port, e);
    }
  }

  /**
   * Registers a listener for service events
   *
   * @param listener A listener
   */
  public void addListener(@NotNull ReceiverListener listener) {
    myListeners.add(listener);
  }

  private void fireMessageReceived(@NotNull Message message) {
    myListeners.forEach(listener -> listener.messageReceived(message));
  }

  private class MyConnectionHandler implements Runnable {
    private final Socket myClientSocket;

    MyConnectionHandler(@NotNull Socket socket) {
      myClientSocket = socket;
    }

    @Override
    public void run() {
      final NamedUser user;
      final byte[] data;
      try (final Socket clientSocket = myClientSocket;
           final DataInputStream in = new DataInputStream(clientSocket.getInputStream());
           final DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

        final InetAddress address = clientSocket.getInetAddress();

        final String userName = in.readUTF();
        final int clientPort = in.readInt();
        final int dataLength = in.readInt();

        user = new UserImpl(userName, address, clientPort);

        data = new byte[dataLength];
        IOUtils.readFully(in, data);

        out.writeInt(AnswerCodes.OK);
      } catch (IOException e) {
        LOG.warn("message receiving failed", e);
        return;
      }

      fireMessageReceived(new TextMessage(user, new Date(), new String(data)));
    }
  }
}
