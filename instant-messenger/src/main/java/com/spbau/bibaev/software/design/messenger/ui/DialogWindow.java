package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.app.Application;
import com.spbau.bibaev.software.design.messenger.app.NamedUser;
import com.spbau.bibaev.software.design.messenger.app.Settings;
import com.spbau.bibaev.software.design.messenger.app.UserImpl;
import com.spbau.bibaev.software.design.messenger.client.Message;
import com.spbau.bibaev.software.design.messenger.client.MessageSendingCallback;
import com.spbau.bibaev.software.design.messenger.client.MessageSendingService;
import com.spbau.bibaev.software.design.messenger.client.TextMessage;
import com.spbau.bibaev.software.design.messenger.server.MessageReceiverService;
import com.spbau.bibaev.software.design.messenger.server.ReceiverListener;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

class DialogWindow extends JFrame {
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM hh:mm");
  private final JTextArea myTextArea = new JTextArea();
  private final JTextField myMessageTextField = new JTextField();
  private volatile NamedUser myUser;

  DialogWindow(@NotNull InetAddress address, int port) throws HeadlessException {
    super(String.format("%s: %d", address, port));
    myTextArea.setEditable(false);
    myMessageTextField.setToolTipText("Enter your message here");

    setDefaultCloseOperation(HIDE_ON_CLOSE);
    setPreferredSize(new Dimension(500, 500));
    JPanel pane = new JPanel(new BorderLayout());
    pane.add(myTextArea, BorderLayout.CENTER);
    JPanel southPane = new JPanel(new BorderLayout());
    southPane.add(new JSeparator(JSeparator.HORIZONTAL));
    southPane.add(myMessageTextField, BorderLayout.CENTER);

    JButton sendButton = new JButton("Send");
    southPane.add(sendButton, BorderLayout.EAST);
    myUser = new UserImpl("unknown", address, port);

    MessageReceiverService receiverService = Application.getInstance().getService(MessageReceiverService.class);
    receiverService.addListener(new ReceiverListener() {
      @Override
      public void messageReceived(@NotNull Message message) {
        if (!myUser.equals(message.getUser())) {
          return;
        }

        myUser = message.getUser();
        if (message instanceof TextMessage) {
          TextMessage textMessage = (TextMessage) message;
          SwingUtilities.invokeLater(
              () -> showMessage(myUser.getName(), textMessage.getDate(), textMessage.getText()));
        } else {
          SwingUtilities.invokeLater(
              () -> showMessage("messenger", new Date(), "unknown message type :("));
        }
      }
    });

    MessageSendingService sendingService = Application.getInstance().getService(MessageSendingService.class);

    sendButton.addActionListener(e -> {
      final String text = myMessageTextField.getText();
      if (!text.trim().isEmpty()) {
        Date date = new Date();
        final TextMessage message = new TextMessage(myUser, date, text);
        String myName = Settings.getInstance().getName();
        sendingService.sendMessage(myName, message, new MessageSendingCallback() {
          @Override
          public void onSuccess() {
            SwingUtilities.invokeLater(() -> showMessage(myName, date, text));
          }

          @Override
          public void onFail(@NotNull Message message, @NotNull Throwable e) {
            myTextArea.append("failed: " + e.toString());
          }
        });
      }
    });

    pane.add(southPane, BorderLayout.SOUTH);
    getContentPane().add(pane);
    getRootPane().setDefaultButton(sendButton);

    pack();
  }

  private void showMessage(@NotNull String author, @NotNull Date date, @NotNull String message) {
    String header = String.format("%s(%s): \n", author,
        DATE_FORMAT.format(date));
    myTextArea.append(header);
    myTextArea.append(message);
  }
}
