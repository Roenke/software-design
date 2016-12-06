package com.spbau.bibaev.software.design.messenger.server;

import com.spbau.bibaev.software.design.messenger.app.Service;
import com.spbau.bibaev.software.design.messenger.client.MessageType;
import com.spbau.bibaev.software.design.messenger.common.AnswerCodes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageReceiverService implements Service {
  private final ExecutorService myThreadPool;

  private final Map<Integer, ServerSocket> myPort2OpenSocket = new ConcurrentHashMap<>();

  public MessageReceiverService(int handlersThreadCount) {
    myThreadPool = Executors.newFixedThreadPool(handlersThreadCount);
  }

  public void attach(int port) {

  }

  public void dettach(int port) {

  }

  private static class MyConnectionListener implements Runnable {
    private final int myPort;

    MyConnectionListener(int port) {
      myPort = port;
    }

    @Override
    public void run() {
      try {
        ServerSocket socket = new ServerSocket(myPort);
        while (!socket.isClosed()) {
          Socket clientSocket = socket.accept();
          DataInputStream in = new DataInputStream(clientSocket.getInputStream());
          DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
          int messageType = in.readInt();
          if (0 < messageType && messageType < MessageType.values().length) {
            MessageType type = MessageType.values()[messageType];
            
          } else {
            out.writeInt(AnswerCodes.UNKNOWN_MESSAGE_TYPE);
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
