package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.Environment;
import com.spbau.bibaev.software.desing.shell.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import com.spbau.bibaev.software.desing.shell.util.TextUtil;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;

public class AssignCommand extends CommandBase {
  public AssignCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  public static boolean isAssignExpression(@NotNull String name) {
    int eqIndex = name.indexOf('=');
    return eqIndex != -1 && TextUtil.isIdentifier(name.substring(0, eqIndex));
  }

  @NotNull
  @Override
  public ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err)
      throws IOException {
    String expression = getName();
    int eqIndex = expression.indexOf('=');
    Environment.getInstance().putVariableValue(expression.substring(0, eqIndex), expression.substring(eqIndex + 1));
    return ExecutionResult.CONTINUE;
  }
}
