package com.spbau.bibaev.software.desing.shell.command.impl;

import com.spbau.bibaev.software.desing.shell.EnvironmentImpl;
import com.spbau.bibaev.software.desing.shell.command.CommandBase;
import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import com.spbau.bibaev.software.desing.shell.util.IoStreamUtils;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Command for reading data from files or console
 *
 * @author Vitaliy.Bibaev
 * @see CommandBase
 */
public class CatCommand extends CommandBase {
  /**
   * Constructs a new command
   *
   * @param name The name of this command
   * @param args The list of arguments. Each argument represent relative path to file
   */
  public CatCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out,
                                 @NotNull OutputStream err) throws IOException {
    List<String> args = getArgs();
    if (args.size() == 0) {
      IoStreamUtils.copy(in, out);
      return ExecutionResult.CONTINUE;
    }

    for (String filename : getArgs()) {
      Path currentDirectory = EnvironmentImpl.getInstance().getCurrentDirectory();
      File file = currentDirectory.resolve(filename).toFile();
      if (!file.exists()) {
        err.write(String.format("File %s not found", filename).getBytes());
        err.write(System.lineSeparator().getBytes());
      }
      Files.copy(file.toPath(), out);
    }

    return ExecutionResult.CONTINUE;
  }
}
