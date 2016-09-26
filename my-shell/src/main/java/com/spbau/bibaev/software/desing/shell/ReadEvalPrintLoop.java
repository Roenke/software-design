package com.spbau.bibaev.software.desing.shell;

import com.spbau.bibaev.software.desing.shell.command.Executable;
import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.ex.ParseException;
import com.spbau.bibaev.software.desing.shell.parsing.InputParser;
import com.spbau.bibaev.software.desing.shell.parsing.Parser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The read-evaluate-print loop
 *
 * @author Vitaliy.Bibaev
 */
class ReadEvalPrintLoop {
  private static final Logger LOG = LogManager.getLogger(ReadEvalPrintLoop.class);

  /**
   * Starts the loop
   */
  static void start() {
    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    Parser<Executable> parser = new InputParser();
    ExecutionResult result = ExecutionResult.CONTINUE;
    while (result != ExecutionResult.SHUTDOWN) {
      try {
        final String userInput = consoleReader.readLine();
        if (userInput.trim().isEmpty()) {
          continue;
        }

        final Executable command = parser.parse(userInput);
        result = command.perform(System.in, System.out, System.err);
      } catch (IOException e) {
        LOG.info(e);
      } catch (ParseException e) {
        LOG.error(e);
      }
    }
  }
}
