package rearobot.commands;

import rearobot.table.Coordinates;
import rearobot.table.Direction;

import java.util.Optional;

import static java.util.Optional.empty;
import static rearobot.Preconditions.checkNotNull;

public final class CommandDefinitionParser {
    private CommandDefinitionParser() {}

    /**
     * Parses command definition (case sensitive) into an instance of {@link Command}.
     *
     * @throws NullPointerException {@code definition} is NULL
     * @throws InvalidCommandException {@code definition} represents invalid or unknown command
     */
    public static Command parse(String definition)
    throws InvalidCommandException {
        checkNotNull(definition, "definition must not be null");
        Optional<Command> command = tryPlaceCommand(definition);
        if (!command.isPresent()) {
            command = tryMoveCommand(definition);
        }
        if (!command.isPresent()) {
            command = tryLeftCommand(definition);
        }
        if (!command.isPresent()) {
            command = tryRightCommand(definition);
        }
        if (!command.isPresent()) {
            command = tryReportCommand(definition);
        }
        if (command.isPresent()) {
            return command.get();
        }
        throw new InvalidCommandException();
    }

    private static Optional<Command> tryPlaceCommand(String definition) {
        if (!definition.startsWith("PLACE ")) {
            return empty();
        }
        String[] array = definition.replaceFirst("PLACE ", "").split(",");
        if (array.length != 3) {
            return empty();
        }
        try {
            Coordinates coordinates = new Coordinates(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
            Direction direction = Direction.valueOf(array[2]);
            return Optional.of(new PlaceCommand(coordinates, direction));
        } catch (IllegalArgumentException e) {
            return empty();
        }
    }

    private static Optional<Command> tryMoveCommand(String definition) {
        return definition.equals("MOVE")
                ? Optional.of(new MoveCommand())
                : empty();
    }

    private static Optional<Command> tryLeftCommand(String definition) {
        return definition.equals("LEFT")
                ? Optional.of(new LeftCommand())
                : empty();
    }

    private static Optional<Command> tryRightCommand(String definition) {
        return definition.equals("RIGHT")
                ? Optional.of(new RightCommand())
                : empty();
    }

    private static Optional<Command> tryReportCommand(String definition) {
        return definition.equals("REPORT")
                ? Optional.of(new ReportCommand())
                : empty();
    }
}
