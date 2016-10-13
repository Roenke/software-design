package com.spbau.bibaev.software.design.shell.command.impl;

import com.spbau.bibaev.software.design.shell.EnvironmentImpl;
import com.spbau.bibaev.software.design.shell.command.CommandBase;
import com.spbau.bibaev.software.design.shell.command.ExecutionResult;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The command for searching by a regular expression
 *
 * @author Vitaliy.Bibaev
 * @see CommandBase
 */
public class GrepCommand extends CommandBase {

  /**
   * Constructs a new instance of {@link GrepCommand}
   *
   * @param name the name of command
   * @param args the list of command arguments
   */
  public GrepCommand(@NotNull String name, @NotNull List<String> args) {
    super(name, args);
  }

  @NotNull
  @Override
  public ExecutionResult execute(@NotNull InputStream in, @NotNull OutputStream out, @NotNull OutputStream err)
      throws IOException {
    final ArgumentParser parser = createParser();
    List<String> listArgs = getArgs();
    String[] args = new String[listArgs.size()];
    listArgs.toArray(args);
    try {
      Namespace parsingResult = parser.parseArgs(args);
      String pattern = parsingResult.getString("pattern");
      String filePath = parsingResult.getString("file");
      boolean ignoreCase = parsingResult.getBoolean("i");
      boolean wordsOnly = parsingResult.getBoolean("w");
      int linesAfterMatch = parsingResult.get("A");

      if (filePath != null) {
        Path path = EnvironmentImpl.getInstance().getCurrentDirectory().resolve(filePath);
        if (!path.toFile().exists()) {
          err.write(String.format("File \"%s\" not found%n", path.toAbsolutePath().toString()).getBytes());
          return ExecutionResult.CONTINUE;
        }
        if (!path.toFile().canRead()) {
          err.write(String.format("File \"%s\" could not be read%n", path.toAbsolutePath().toString()).getBytes());
          return ExecutionResult.CONTINUE;
        }

        try (InputStream is = Files.newInputStream(path)) {
          grepInternal(is, out, pattern, ignoreCase, wordsOnly, linesAfterMatch);
        }
      }

      grepInternal(in, out, pattern, ignoreCase, wordsOnly, linesAfterMatch);

      return ExecutionResult.CONTINUE;
    } catch (ArgumentParserException e) {
      parser.handleError(e);
      return ExecutionResult.CONTINUE;
    }
  }

  private static void grepInternal(@NotNull InputStream in, @NotNull OutputStream out, String pattern,
                                   boolean ignoreCase, boolean wordsOnly, int linesAfterMatch) {
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    PrintStream writer = new PrintStream(out);
    Pattern compiledPattern = createRegexPattern(pattern, ignoreCase, wordsOnly);
    Iterable<String> lines = () -> reader.lines().iterator();
    int printedUnmatchedAfterMatch = linesAfterMatch;
    for (String line : lines) {
      if (compiledPattern.matcher(line).find()) {
        printedUnmatchedAfterMatch = 0;
        writer.println(line);
      } else {
        if (printedUnmatchedAfterMatch < linesAfterMatch) {
          printedUnmatchedAfterMatch++;
          writer.println(line);
        }
      }
    }
  }

  private static Pattern createRegexPattern(@NotNull String pattern, boolean ignoreCase, boolean wordsOnly) {
    if (wordsOnly) {
      pattern = String.format("\\b%s\\b", pattern);
    }

    int flags = ignoreCase ? Pattern.CASE_INSENSITIVE : 0;
    return Pattern.compile(pattern, flags);
  }

  private static ArgumentParser createParser() {
    ArgumentParser parser = ArgumentParsers.newArgumentParser("grep")
        .description("Globally search a regular expression and print")
        .defaultHelp(true);

    parser.addArgument("pattern")
        .type(String.class)
        .metavar("pattern")
        .help("regex pattern");

    parser.addArgument("file")
        .nargs("?")
        .type(String.class)
        .help("path to file");

    parser.addArgument("-i")
        .action(Arguments.storeTrue())
        .help("ignore case");

    parser.addArgument("-w")
        .action(Arguments.storeTrue())
        .help("search only entire words");

    parser.addArgument("-A")
        .metavar("n")
        .type(Integer.class)
        .choices(Arguments.range(0, Integer.MAX_VALUE))
        .setDefault(0)
        .help("also print next n lines after each match");
    return parser;
  }
}
