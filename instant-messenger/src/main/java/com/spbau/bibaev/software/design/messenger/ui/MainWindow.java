package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.app.Application;
import com.spbau.bibaev.software.design.messenger.app.Settings;
import com.spbau.bibaev.software.design.messenger.server.MessageReceiverService;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
  private final String myName = "default";
  private final JLabel myInformationLabel = new JLabel();

  public MainWindow() {
    super("Instant messenger");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    addWindowListener(new MyWindowClosedListener());

    setLocationRelativeTo(null);

    final Settings settings = Settings.getInstance();
    final MessageReceiverService receiverService = Application.getInstance().getService(MessageReceiverService.class);

    JPanel informationPanel = new JPanel();
    settings.addListener((setts, propertyName) -> updateInformation(setts));
    updateInformation(settings);
    informationPanel.add(myInformationLabel);

    JPanel buttonsPane = new JPanel(new GridLayout(2, 1));
    final JButton sendMessageButton = new JButton("Send message");
    final JButton settingsButton = new JButton("Settings");

    settingsButton.addActionListener(e -> {
      final SettingsWindow settingsWindow = new SettingsWindow();
      settingsWindow.setVisible(true);
    });

    buttonsPane.add(sendMessageButton);
    buttonsPane.add(settingsButton);

    final JPanel pane = new JPanel(new BorderLayout());
    pane.add(informationPanel, BorderLayout.NORTH);
    pane.add(buttonsPane, BorderLayout.CENTER);

    getContentPane().add(pane);

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

  private void updateInformation(@NotNull Settings settings) {
    if (SwingUtilities.isEventDispatchThread()) {
      final String info = String.format("Current settings: name = %s; port = %d",
          settings.getName(), settings.getPort());
      myInformationLabel.setText(info);
    } else {
      SwingUtilities.invokeLater(() -> updateInformation(settings));
    }
  }

}
