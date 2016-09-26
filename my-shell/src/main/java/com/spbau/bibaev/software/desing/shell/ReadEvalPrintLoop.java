package com.spbau.bibaev.software.desing.shell;

import com.spbau.bibaev.software.desing.shell.command.Executable;
import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.ex.EmptyCommandException;
import com.spbau.bibaev.software.desing.shell.parsing.InputParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ReadEvalPrintLoop {
  private static final Logger LOG = LogManager.getLogger(ReadEvalPrintLoop.class);

  static void start() {
    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    ExecutionResult result = ExecutionResult.CONTINUE;
    while (result != ExecutionResult.SHUTDOWN) {
      try {
        final String userInput = consoleReader.readLine();
        if(userInput.trim().isEmpty()) {
          continue;
        }

        final Executable command = InputParser.parse(userInput);
        result = command.perform(System.in, System.out, System.err);
      } catch (IOException e) {
        LOG.info(e);
      } catch (EmptyCommandException e) {
        LOG.error(e);
      }
    }
  }
}
