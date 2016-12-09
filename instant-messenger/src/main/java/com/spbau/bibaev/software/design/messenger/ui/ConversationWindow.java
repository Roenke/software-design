package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.app.Application;
import com.spbau.bibaev.software.design.messenger.app.NamedUser;
import com.spbau.bibaev.software.design.messenger.app.Settings;
import com.spbau.bibaev.software.design.messenger.app.UserImpl;
import com.spbau.bibaev.software.design.messenger.client.Message;
import com.spbau.bibaev.software.design.messenger.client.MessageSendingCallback;
import com.spbau.bibaev.software.design.messenger.client.MessageSendingService;
import com.spbau.bibaev.software.design.messenger.client.TextMessage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A non-modal window for user chat
 *
 * @author Vitaliy.Bibaev
 */
class ConversationWindow extends JFrame {
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM hh:mm");
  private final JTextArea myTextArea = new JTextArea();
  private final JTextField myMessageTextField = new JTextField();
  private volatile NamedUser myUser;

  /**
   * Constructs a new instance of the window
   *
   * @param address An address of the user
   * @param port    A port which the user listening
   */
  ConversationWindow(@NotNull InetAddress address, int port) {
    super(String.format("%s: %d", address, port));
    myTextArea.setEditable(false);
    myMessageTextField.setToolTipText("Enter your message here");

    setDefaultCloseOperation(HIDE_ON_CLOSE);
    setPreferredSize(new Dimension(500, 500));

    final JPanel pane = new JPanel(new BorderLayout());
    pane.add(myTextArea, BorderLayout.CENTER);
    final JPanel southPane = new JPanel(new BorderLayout());
    southPane.add(new JSeparator(JSeparator.HORIZONTAL));
    southPane.add(myMessageTextField, BorderLayout.CENTER);

    final JButton sendButton = new JButton("Send");
    southPane.add(sendButton, BorderLayout.EAST);
    myUser = new UserImpl("unknown", address, port);

    sendButton.addActionListener(e -> {
      final String text = myMessageTextField.getText();
      if (!text.trim().isEmpty()) {
        final Date date = new Date();
        final TextMessage message = new TextMessage(myUser, date, text);
        Application.getInstance().getService(MessageSendingService.class)
            .sendMessage(Settings.getInstance().getName(), message, new MessageSendingCallback() {
              @Override
              public void onSuccess(@NotNull Message message) {
                showMessage(Settings.getInstance().getName(), message.getDate(), text);
                myMessageTextField.setText("");
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

  /**
   * Add message to the list of messages
   *
   * @param message A message
   */
  void pushMessage(@NotNull Message message) {
    if (myUser != null && !myUser.equals(message.getUser())) {
      return;
    }

    myUser = message.getUser();
    if (message instanceof TextMessage) {
      final TextMessage textMessage = (TextMessage) message;
      showMessage(myUser.getName(), textMessage.getDate(), textMessage.getText());
    } else {
      showMessage("messenger", new Date(), "unknown message type :(");
    }
  }

  private void showMessage(@NotNull String author, @NotNull Date date, @NotNull String message) {
    if (SwingUtilities.isEventDispatchThread()) {
      final String header = String.format("%s(%s): %n", author,
          DATE_FORMAT.format(date));
      myTextArea.append(header);
      myTextArea.append(String.format("%s%n", message));
    } else {
      SwingUtilities.invokeLater(() -> showMessage(author, date, message));
    }
  }
}
