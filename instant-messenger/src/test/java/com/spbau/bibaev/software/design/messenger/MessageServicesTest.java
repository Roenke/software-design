package com.spbau.bibaev.software.design.messenger;

import com.spbau.bibaev.software.design.messenger.app.UserImpl;
import com.spbau.bibaev.software.design.messenger.client.Message;
import com.spbau.bibaev.software.design.messenger.client.MessageSendingCallback;
import com.spbau.bibaev.software.design.messenger.client.MessageSendingService;
import com.spbau.bibaev.software.design.messenger.client.TextMessage;
import com.spbau.bibaev.software.design.messenger.server.MessageReceiverService;
import com.spbau.bibaev.software.design.messenger.server.ReceiverListener;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.Assert.*;

/**
 * @author Vitaliy.Bibaev
 */
public class MessageServicesTest {
  private static final int PORT = 30001;

  @Test
  public void sendReceiveMessageTest() throws UnknownHostException, BrokenBarrierException, InterruptedException {
    final MessageReceiverService receiverService = new MessageReceiverService(1);
    final MessageSendingService sendingService = new MessageSendingService(1);

    String from = "Bob";
    String to = "Alice";
    String text = "hello!";
    Date now = new Date();
    AtomicBoolean failed = new AtomicBoolean(false);

    CyclicBarrier barrier = new CyclicBarrier(3);
    receiverService.attach(PORT);
    AtomicReference<Message> receivedMessage = new AtomicReference<>();
    AtomicReference<Message> sentMessage = new AtomicReference<>();

    receiverService.addListener(new ReceiverListener() {
      @Override
      public void messageReceived(@NotNull Message message) {
        try {
          receivedMessage.set(message);
          barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
          failed.set(true);
        }
      }
    });

    sendingService.sendMessage(from, new TextMessage(new UserImpl(to, InetAddress.getLocalHost(), PORT), now, text),
        new MessageSendingCallback() {
          @Override
          public void onSuccess(@NotNull Message message) {
            try {
              sentMessage.set(message);
              barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
              failed.set(true);
            }
          }

          @Override
          public void onFail(@NotNull Message message, @NotNull Throwable e) {
            try {
              failed.set(true);
              barrier.await();
            } catch (InterruptedException | BrokenBarrierException e1) {
              e1.printStackTrace();
            }
          }
        });

    barrier.await();
    assertEquals(to, sentMessage.get().getUser().getName());
    assertEquals(from, receivedMessage.get().getUser().getName());

    assertArrayEquals(sentMessage.get().getData(), receivedMessage.get().getData());
    assertEquals(now, sentMessage.get().getDate());

    assertFalse(failed.get());
  }
}
