package com.spbau.bibaev.software.design.messenger.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
  private final ConversationList myConversationList = new ConversationList();

  public MainWindow() {
    super("Instant messenger");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    addWindowListener(new MyWindowClosedListener());
    setPreferredSize(new Dimension(500, 500));
    JTextArea textArea = new JTextArea("Hello!");
    textArea.setLineWrap(true);
    textArea.setEditable(false);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (int) ((dimension.getWidth() / 2 - 250));
    int y = (int) ((dimension.getHeight() / 2 - 250));
    setLocation(x, y);
    textArea.setBorder(BorderFactory.createLineBorder(Color.black, 2));
    JPanel pane = new JPanel(new BorderLayout());
    pane.add(new JLabel("Conversations", SwingConstants.CENTER), BorderLayout.NORTH);
    pane.add(myConversationList, BorderLayout.CENTER);
    pane.add(new JButton("Settings"), BorderLayout.SOUTH);
    getContentPane().add(pane);

    myConversationList.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
          int clickedIndex = myConversationList.locationToIndex(e.getPoint());
          if (clickedIndex != -1) {
            Conversation conversation = myConversationList.getModel().getElementAt(clickedIndex);
            new DialogWindow(conversation).setVisible(true);
          }
        }
      }
    });

    myConversationList.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        int index = myConversationList.getSelectedIndex();
        if (index != -1 && e.getKeyCode() == KeyEvent.VK_ENTER) {
          Conversation conversation = myConversationList.getModel().getElementAt(index);
          new DialogWindow(conversation).setVisible(true);
        }
      }
    });
    pack();
  }

  private static class MyWindowClosedListener extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
      super.windowClosing(e);
    }

    @Override
    public void windowClosed(WindowEvent e) {
      super.windowClosed(e);
    }
  }
}
