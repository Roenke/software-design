package com.spbau.bibaev.software.design.messenger.client;

import com.spbau.bibaev.software.design.messenger.app.Service;
import com.spbau.bibaev.software.design.messenger.app.Settings;
import com.spbau.bibaev.software.design.messenger.common.AnswerCodes;
import com.spbau.bibaev.software.design.messenger.ex.ProtocolException;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageSendingService implements Service {
  private static final Logger LOG = LogManager.getLogger(MessageSendingService.class);

  private static final MessageSendingCallback EMPTY_CALLBACK = new MessageSendingCallback() {
    @Override
    public void onSuccess(@NotNull Message message) {
    }

    @Override
    public void onFail(@NotNull Message message, @NotNull Throwable e) {
    }
  };
  private final ExecutorService myThreadPool;

  public MessageSendingService(int threadCount) {
    myThreadPool = Executors.newFixedThreadPool(threadCount);
  }

  public void sendMessage(@NotNull String fromName,
                          @NotNull Message message,
                          @Nullable MessageSendingCallback callback) {
    myThreadPool.execute(new MySendMessageTask(fromName, message, callback == null ? EMPTY_CALLBACK : callback));
  }

  private static class MySendMessageTask implements Runnable {
    private final Message myMessage;
    private final String myName;
    private final MessageSendingCallback myCallback;

    MySendMessageTask(@NotNull String name, @NotNull Message message, @NotNull MessageSendingCallback callback) {
      myMessage = message;
      myCallback = callback;
      myName = name;
    }

    @Override
    public void run() {
      try {
        send();
        myCallback.onSuccess(myMessage);
        LOG.info("Message to " + myMessage.getUser().getName() + " successfully delivered");
      } catch (IOException e) {
        LOG.warn("Message sending failed", e);
        myCallback.onFail(myMessage, e);
      }
    }

    private void send() throws IOException {
      try (Socket socket = new Socket(myMessage.getUser().getAddress(), myMessage.getUser().getPort());
           DataInputStream in = new DataInputStream(socket.getInputStream());
           DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

        final byte[] data = myMessage.getData();

        out.writeUTF(myName);
        out.writeInt(Settings.getInstance().getPort());
        out.writeInt(data.length);

        IOUtils.copyLarge(new ByteArrayInputStream(data), out);
        final int result = in.readInt();

        if (result != AnswerCodes.OK) {
          throw new ProtocolException(result);
        }
      }
    }
  }
}
