package com.spbau.bibaev.software.desing.shell;

import com.spbau.bibaev.software.desing.shell.ex.CommandCreationException;
import com.spbau.bibaev.software.desing.shell.ex.EmptyCommandException;
import com.spbau.bibaev.software.desing.shell.parsing.CommandParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

class ReadEvalPrintLoop {
  private static final Logger LOG = LogManager.getLogger(ReadEvalPrintLoop.class);

  static void start() {
    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    //noinspection InfiniteLoopStatement
    while (true) {
      try {
        final String userInput = consoleReader.readLine();
        final Executable command = CommandParser.parse(userInput);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        command.perform(reader, writer);
      } catch (IOException e) {
        LOG.info(e);
      } catch (EmptyCommandException | CommandCreationException e) {
        LOG.error(e);
      }
    }
  }
}
