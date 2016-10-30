package com.spbau.bibaev.software.design.messenger.ui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class DialogWindow extends JFrame {
  private final Conversation myConversation;
  private final JTextArea myTextArea = new JTextArea();
  private final JButton mySendButton = new JButton("Send");
  private final JTextField myMessageTextField = new JTextField();

  public DialogWindow(@NotNull Conversation conversation) throws HeadlessException {
    super(conversation.getUserName());
    myConversation = conversation;
    setDefaultCloseOperation(HIDE_ON_CLOSE);
    setPreferredSize(new Dimension(500, 500));
    JPanel pane = new JPanel(new BorderLayout());
    pane.add(myTextArea, BorderLayout.CENTER);
    JPanel southPane = new JPanel();
    southPane.add(myMessageTextField, BorderLayout.CENTER);
    southPane.add(mySendButton, BorderLayout.EAST);
    pane.add(southPane, BorderLayout.SOUTH);
    getContentPane().add(pane);
    pack();
  }
}
