package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.app.User;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConversationList extends JList<Conversation> {
  private final List<Conversation> myConversations = new ArrayList<>();
  public ConversationList() {
    super();
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    try {
      myConversations.add(new Conversation(new User("Bob", InetAddress.getByAddress("localhost", new byte[] {127, 0, 0, 1})), Collections.emptyList()));
      myConversations.add(new Conversation(new User("Alice", InetAddress.getByAddress(new byte[] {127, 0, 0, 1})), Collections.emptyList()));
    } catch (UnknownHostException e) {
      throw new RuntimeException();
    }
    setModel(new DefaultListModel<Conversation>() {
      @Override
      public int getSize() {
        return myConversations.size();
      }

      @Override
      public Conversation getElementAt(int index) {
        return myConversations.get(index);
      }
    });
  }
}
