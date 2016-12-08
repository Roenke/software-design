package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.app.Application;
import com.spbau.bibaev.software.design.messenger.client.Message;
import com.spbau.bibaev.software.design.messenger.client.MessageSendingCallback;
import com.spbau.bibaev.software.design.messenger.client.MessageSendingService;
import com.spbau.bibaev.software.design.messenger.client.TextMessage;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DialogWindow extends JFrame {
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM hh:mm");
  private final Conversation myConversation;
  private final JTextArea myTextArea = new JTextArea();
  private final JButton mySendButton = new JButton("Send");
  private final JTextField myMessageTextField = new JTextField();

  public DialogWindow(@NotNull Conversation conversation) throws HeadlessException {
    super(conversation.getUserName());
    myConversation = conversation;
    myTextArea.setEditable(false);
    myMessageTextField.setToolTipText("Enter your message here");

    setDefaultCloseOperation(HIDE_ON_CLOSE);
    setPreferredSize(new Dimension(500, 500));
    JPanel pane = new JPanel(new BorderLayout());
    pane.add(myTextArea, BorderLayout.CENTER);
    JPanel southPane = new JPanel(new BorderLayout());
    southPane.add(new JSeparator(JSeparator.HORIZONTAL));
    southPane.add(myMessageTextField, BorderLayout.CENTER);
    southPane.add(mySendButton, BorderLayout.EAST);

    MessageSendingService sendingService = Application.getInstance().getService(MessageSendingService.class);

    mySendButton.addActionListener(e -> {
      final String text = myMessageTextField.getText();
      if (!text.trim().isEmpty()) {
        Date date = new Date();
        final TextMessage message = new TextMessage(conversation.getUser(), date, text);
        sendingService.sendMessage("kdlfgjlkdfg", message, new MessageSendingCallback() {
          @Override
          public void onSuccess() {
            String header = String.format("%s(%s): \n", message.getUser().getName(),
                DATE_FORMAT.format(message.getDate()));
            myTextArea.append(header);
            myTextArea.append(message.getText());
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

    pack();
  }

  private static class MyMessage {

  }
}
