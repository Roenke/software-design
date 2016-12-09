package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.app.Settings;
import com.spbau.bibaev.software.design.messenger.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class SettingsWindow extends JDialog {
  private static final int PADDING_SIZE = 15;
  private final JTextField myUsernameTextField;
  private final JTextField myPortTextField;
  private final JButton mySaveButton = new JButton("Save");

  SettingsWindow(@NotNull Frame owner) throws HeadlessException {
    super(owner, "Settings", ModalityType.APPLICATION_MODAL);

    final Settings settings = Settings.getInstance();
    myUsernameTextField = new JTextField(settings.getName(), 12);
    myPortTextField = new JTextField(String.valueOf(settings.getPort()), 12);
    setDefaultCloseOperation(HIDE_ON_CLOSE);
    JLabel usernameLabel = new JLabel("UserImpl Name: ");
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

    mySaveButton.addActionListener(e -> {
      Settings.getInstance().setName(myUsernameTextField.getText());
      Settings.getInstance().setPort(Integer.parseInt(myPortTextField.getText()));
      dispose();
    });

    layout.setHorizontalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(usernameLabel)
            .addComponent(portLabel))
        .addGroup(layout.createParallelGroup()
            .addComponent(myUsernameTextField)
            .addComponent(myPortTextField)));

    layout.setVerticalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(usernameLabel)
            .addComponent(myUsernameTextField))
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(portLabel)
            .addComponent(myPortTextField)));

    getRootPane().setDefaultButton(mySaveButton);
    JPanel pane = new JPanel();
    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

    pane.add(configPane);
    pane.add(mySaveButton);
    pane.setBorder(BorderFactory.createEmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));

    getContentPane().add(pane);

    setLocationRelativeTo(null);

    pack();
  }

  private class MyPortInputValidator extends InputVerifier {
    @Override
    public boolean verify(JComponent input) {
      return input instanceof JTextField && StringUtil.isContainsPortNumber(((JTextField) input).getText());
    }

    @Override
    public boolean shouldYieldFocus(JComponent input) {
      final boolean before = mySaveButton.isEnabled();
      final boolean result = verify(input);
      if (result != before) {
        input.setForeground(result ? Color.black : Color.red);
        mySaveButton.setEnabled(result);
      }

      return true;
    }
  }
}
