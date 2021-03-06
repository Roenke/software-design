package com.spbau.bibaev.software.design.shell.command.impl;

import com.spbau.bibaev.software.design.shell.EnvironmentImpl;
import com.spbau.bibaev.software.design.shell.command.CommandBase;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import com.spbau.bibaev.software.design.shell.util.TextUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Update execution environment - add or update global variable
 * <p>usage: name=value</p>
 * <p>name should be a valid identifier</p>
 * <p>value is any string</p>
 *
 * @author Vitaliy.Bibaev
 * @see CommandBase
 */
public class AssignCommand extends CommandBase {
  private static final char ASSIGN_CHARACTER = '=';

  /**
   * Construct a new assignment command
   *
   * @param name name of command - valid assignment expression
   * @param args empty list of arguments
   */
  public AssignCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  /**
   * Checks that expression can be interpreted as assignment expression. Expression pattern: {@code variable=value}
   *
   * @param expression the candidate to be an expression
   * @return true is {@code expression} can be interpreted as assignment, false otherwise
   */
  public static boolean isAssignExpression(@NotNull String expression) {
    expression = expression.trim();
    int eqIndex = expression.indexOf('=');
    return eqIndex != -1 && eqIndex != expression.length() - 1
        && TextUtil.isIdentifier(expression.substring(0, eqIndex));
  }

  @NotNull
  @Override
  public ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err)
      throws IOException {
    String expression = getName();
    int eqIndex = expression.indexOf(ASSIGN_CHARACTER);
    EnvironmentImpl.getInstance().putVariableValue(expression.substring(0, eqIndex), expression.substring(eqIndex + 1));
    return ExecutionResult.CONTINUE;
  }
}
