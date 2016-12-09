package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.EntryPoint;
import com.spbau.bibaev.software.design.messenger.app.User;
import com.spbau.bibaev.software.design.messenger.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Vitaliy.Bibaev
 */
class ConnectionDialog extends JDialog {
  private static final int PADDING_SIZE = 15;
  private final JTextField myAddressTextField;
  private final JTextField myPortTextField;
  private final JButton myOpenChatButton = new JButton("Open");
  private final JLabel myErrorLabel = new JLabel("Wrong address");
  private int myPortResult;
  private InetAddress myAddressResult;

  ConnectionDialog(@NotNull Frame owner) throws HeadlessException {
    super(owner, "Connect", ModalityType.APPLICATION_MODAL);
    myAddressTextField = new JTextField("localhost", 15);
    myPortTextField = new JTextField(String.valueOf(EntryPoint.DEFAULT_PORT), 15);

    setDefaultCloseOperation(HIDE_ON_CLOSE);
    JLabel usernameLabel = new JLabel("Address:  ");
    JLabel portLabel = new JLabel("Port: ");

    JPanel configPane = new JPanel();
    GroupLayout layout = new GroupLayout(configPane);
    configPane.setLayout(layout);

    myPortTextField.setInputVerifier(new MyPortInputValidator());
    myPortTextField.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        myPortTextField.getInputVerifier().shouldYieldFocus(myPortTextField);
      }
    });

    myOpenChatButton.addActionListener(e -> {
      try {
        String address = myAddressTextField.getText();
        myAddressResult = InetAddress.getByName(address);
        myPortResult = Integer.parseInt(myPortTextField.getText());
        dispose();
      } catch (UnknownHostException e1) {
        myErrorLabel.setVisible(true);
      }
    });

    layout.setHorizontalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(usernameLabel)
            .addComponent(portLabel))
        .addGroup(layout.createParallelGroup()
            .addComponent(myAddressTextField)
            .addComponent(myPortTextField)));

    layout.setVerticalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(usernameLabel)
            .addComponent(myAddressTextField))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(portLabel)
            .addComponent(myPortTextField)));

    getRootPane().setDefaultButton(myOpenChatButton);
    JPanel pane = new JPanel();
    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

    myErrorLabel.setVisible(false);
    pane.add(configPane);
    pane.add(myOpenChatButton);
    pane.add(myErrorLabel);
    pane.setBorder(BorderFactory.createEmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));

    getContentPane().add(pane);

    setLocationRelativeTo(owner);

    pack();
  }

  @Nullable
  public User showDialog() {
    setVisible(true);
    final int port = myPortResult;
    final InetAddress address = myAddressResult;
    if (port == -1 || address == null) {
      return null;
    }

    return new User() {
      @Override
      public @NotNull InetAddress getAddress() {
        return address;
      }

      @Override
      public int getPort() {
        return port;
      }
    };
  }

  private class MyPortInputValidator extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
      return input instanceof JTextField && StringUtil.isContainsPortNumber(((JTextField) input).getText());
    }

    @Override
    public boolean shouldYieldFocus(JComponent input) {
      final boolean before = myOpenChatButton.isEnabled();
      final boolean result = verify(input);
      if (result != before) {
        input.setForeground(result ? Color.black : Color.red);
        myOpenChatButton.setEnabled(result);
      }

      return true;
    }
  }
}
