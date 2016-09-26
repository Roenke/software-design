package com.spbau.bibaev.software.desing.shell.pipe;

import com.spbau.bibaev.software.desing.shell.command.Executable;
import com.spbau.bibaev.software.desing.shell.command.ExecutionResult;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Handler for commands which connected by pipeline. Executes first command,
 *
 * @author Vitaliy.Bibaev
 * @see Executable
 */
public class PipeHandler implements Executable {
  private final Executable myLeftExecutable;
  private final Executable myRightExecutable;

  /**
   * Constructs new handler
   * @param left The first command for execution
   * @param right The second command for execution
   */
  public PipeHandler(@NotNull Executable left, @NotNull Executable right) {
    myLeftExecutable = left;
    myRightExecutable = right;
  }

  @NotNull
  @Override
  public ExecutionResult perform(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    List<Integer> buffer = new ArrayList<>();
    OutputStream outputStream = new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        buffer.add(b);
      }
    };

    ExecutionResult firstResult = myLeftExecutable.perform(in, outputStream, err);
    if(firstResult == ExecutionResult.SHUTDOWN) {
      return ExecutionResult.SHUTDOWN;
    }

    InputStream inputStream = new InputStream() {
      private int pos = 0;
      @Override
      public int read() throws IOException {
        return pos < buffer.size() ? buffer.get(pos++) : -1;
      }
    };

    return myRightExecutable.perform(inputStream, out, err);
  }
}
