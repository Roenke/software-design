package com.spbau.bibaev.software.desing.shell;

/**
 * Entry point of shell program. Runs REPL
 *
 * @author Vitaliy.Bibaev
 * @see ReadEvalPrintLoop
 */
public class EntryPoint {

  // TODO; add logs
  // TODO: avoid the static methods for parsers

  /**
   * The entry point
   * @param args The command line arguments
   */
  public static void main(String[] args) {
    ReadEvalPrintLoop.start();
  }
}
