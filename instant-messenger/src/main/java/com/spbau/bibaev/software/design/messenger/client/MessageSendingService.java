package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.Service;
import com.spbau.bibaev.software.design.messenger.app.User;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MessageSendingService implements Service {
  private static final int CONNECTION_TIMEOUT = (int) TimeUnit.SECONDS.toMillis(5);
  private static final MessageSendingCallback EMPTY_CALLBACK = new MessageSendingCallback() {
    @Override
    public void onSuccess() {
    }

    @Override
    public void onFail(@NotNull Message message, @NotNull Throwable e) {
    }
  };
  private final ExecutorService myThreadPool;

  public MessageSendingService(int threadCount) {
    myThreadPool = Executors.newFixedThreadPool(threadCount);
  }

  public void sendMessage(@NotNull Message message, @Nullable MessageSendingCallback callback) {
    myThreadPool.execute(new MySendMessageTask(message, callback == null ? EMPTY_CALLBACK : callback));
  }

  private static class MySendMessageTask implements Runnable {
    private final Message myMessage;
    private final MessageSendingCallback myCallback;

    MySendMessageTask(@NotNull Message message, @NotNull MessageSendingCallback callback) {
      myMessage = message;
      myCallback = callback;
    }

    @Override
    public void run() {
      try {
        send();
      } catch (IOException e) {
        myCallback.onFail(myMessage, e);
      }
    }

    private void send() throws IOException {
      try (Socket socket = new Socket()) {
        User receiver = myMessage.getReceiver();
        socket.connect(new InetSocketAddress(receiver.getHost(), receiver.getPort()), CONNECTION_TIMEOUT);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeInt(myMessage.getType().ordinal());
        IOUtils.copyLarge(myMessage.getData(), out);
      }
    }
  }
}
