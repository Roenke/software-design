package com.spbau.bibaev.software.design.messenger.ui;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class DialogWindow extends JFrame {
  private final Conversation myConversation;
  private final JTextArea myTextArea = new JTextArea();
  private final JButton mySendButton = new JButton("Send");
  private final JTextField myMessageTextField = new JTextField("khasdfkjsdfh");

  public DialogWindow(@NotNull Conversation conversation) throws HeadlessException {
    super(conversation.getUserName());
    myConversation = conversation;
    myTextArea.setEditable(false);

    setDefaultCloseOperation(HIDE_ON_CLOSE);
    setPreferredSize(new Dimension(500, 500));
    JPanel pane = new JPanel(new BorderLayout());
    pane.add(myTextArea, BorderLayout.CENTER);
    JPanel southPane = new JPanel(new BorderLayout());
    southPane.add(new JSeparator(JSeparator.HORIZONTAL));
    southPane.add(myMessageTextField, BorderLayout.CENTER);
    southPane.add(mySendButton, BorderLayout.EAST);
    pane.add(southPane, BorderLayout.SOUTH);
    getContentPane().add(pane);

    pack();
  }
}
