package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.app.Settings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

class SettingsDialog extends JDialog {
  private static final int PADDING_SIZE = 15;
  private final JTextField myUsernameTextField;
  private String myResult;

  SettingsDialog(@NotNull Frame owner) throws HeadlessException {
    super(owner, "Settings", ModalityType.APPLICATION_MODAL);

    final Settings settings = Settings.getInstance();
    myUsernameTextField = new JTextField(settings.getName(), 12);
    setDefaultCloseOperation(HIDE_ON_CLOSE);
    JLabel usernameLabel = new JLabel("Username: ");

    JPanel configPane = new JPanel();
    GroupLayout layout = new GroupLayout(configPane);
    configPane.setLayout(layout);

    JButton saveButton = new JButton("Save");
    saveButton.addActionListener(e -> {
      myResult = myUsernameTextField.getText();
      dispose();
    });

    layout.setHorizontalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(usernameLabel))
        .addGroup(layout.createParallelGroup()
            .addComponent(myUsernameTextField)));

    layout.setVerticalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(usernameLabel)
            .addComponent(myUsernameTextField)));

    getRootPane().setDefaultButton(saveButton);
    JPanel pane = new JPanel();
    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

    pane.add(configPane);
    pane.add(Box.createVerticalStrut(6));
    pane.add(saveButton);
    pane.setBorder(BorderFactory.createEmptyBorder(PADDING_SIZE, PADDING_SIZE, PADDING_SIZE, PADDING_SIZE));

    getContentPane().add(pane);

    setLocationRelativeTo(owner);

    pack();
  }

  @Nullable String showDialog() {
    setVisible(true);
    return myResult;
  }
}
