package rearobot;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import rearobot.commands.CommandDefinitionParser;
import rearobot.commands.InvalidCommandException;
import rearobot.robot.Robot;
import rearobot.table.Table;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.System.out;
import static rearobot.Preconditions.checkNotNull;

public class Application {
    public static void main(String[] args) {
        out.println("REA robot simulation.");
        out.println();
        Options options = defineCommandLineOptions();
        try {
            CommandLine commandLine = parseCommandLineOptions(args, options);
            Application application = new Application();
            application.runSimulation(
                    commandLine.getOptionValue("input"),
                    new Robot(new Table(5, 5), out::println)
            );
        } catch (ParseException e) {
            out.println(e.getMessage());
            showUsage(options);
        } catch (ApplicationException e) {
            out.println(e.getMessage());
        }
    }

    private static Options defineCommandLineOptions() {
        Option input = new Option("i", "input", true, "path to a file with commands for the robot");
        input.setRequired(true);
        input.setArgName("input_file");
        Options options = new Options();
        options.addOption(input);
        return options;
    }

    private static CommandLine parseCommandLineOptions(String[] args, Options options)
    throws ParseException {
        CommandLineParser parser = new DefaultParser();
        return parser.parse(options, args);
    }

    private static void showUsage(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("run.sh -i input_file", options);
    }

    void runSimulation(String inputFilePath, Robot robot)
    throws ApplicationException {
        checkNotNull(inputFilePath, "input file path must not be NULL");
        checkNotNull(robot, "robot must not be NULL");
        Path path = Paths.get(inputFilePath);
        if (Files.notExists(path)) {
            throw new ApplicationException(String.format("Input file '%s' does not exist.", path.toAbsolutePath()));
        }
        try {
            Files.lines(path, StandardCharsets.UTF_8)
                    .filter(line -> line.trim().isEmpty())
                    .forEach(line -> {
                        try {
                            CommandDefinitionParser.parse(line).apply(robot);
                        } catch (InvalidCommandException e) {
                            // Ignore invalid command silently and continue processing input file.
                        }
                    });
        } catch (IOException e) {
            throw new ApplicationException("Unable to read input file.");
        }
    }
}
