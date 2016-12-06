package com.spbau.bibaev.software.design.messenger.ui;

import com.spbau.bibaev.software.design.messenger.app.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConversationList extends JList<Conversation> {
  private final List<Conversation> myConversations = new ArrayList<>();

  public ConversationList() {
    super();
    setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    myConversations.add(new Conversation(new User("Bob", "localhost", 8999), Collections.emptyList()));
    myConversations.add(new Conversation(new User("Alice", "localhost", 23423), Collections.emptyList()));
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
