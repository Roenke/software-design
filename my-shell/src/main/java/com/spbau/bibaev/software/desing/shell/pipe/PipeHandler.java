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
   *
   * @param left  The first command for execution
   * @param right The second command for execution
   */
  public PipeHandler(@NotNull Executable left, @NotNull Executable right) {
    myLeftExecutable = left;
    myRightExecutable = right;
  }

  @NotNull
  @Override
  public ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err) throws IOException {
    MySequentialPipe pipe = new MySequentialPipe();

    ExecutionResult firstResult = myLeftExecutable.execute(in, pipe.getOutputStream(), err);
    pipe.getOutputStream().close();
    if (firstResult == ExecutionResult.SHUTDOWN) {
      return ExecutionResult.SHUTDOWN;
    }

    return myRightExecutable.execute(pipe.getInputStream(), out, err);
  }

  /**
   * Utility class for pipe stream supporting
   */
  private static class MySequentialPipe {
    private static final int INITIAL_BUFFER_SIZE = 4096;
    private int myOffset = 0;
    private final List<Integer> myBuffer = new ArrayList<>(INITIAL_BUFFER_SIZE);
    private final InputStream myIn = new InputStream() {
      @Override
      public int read() throws IOException {
        return myOffset < myBuffer.size() ? myBuffer.get(myOffset++) : -1;
      }
    };
    private final OutputStream myOut = new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        myBuffer.add(b);
      }
    };

    InputStream getInputStream() {
      return myIn;
    }

    OutputStream getOutputStream() {
      return myOut;
    }
  }
}
