package rearobot.commands;

import rearobot.robot.Robot;
import rearobot.table.Coordinates;
import rearobot.table.Direction;

class PlaceCommand implements Command {
    private final Coordinates coordinates;
    private final Direction facingDirection;

    PlaceCommand(Coordinates coordinates, Direction facingDirection) {
        this.coordinates = coordinates;
        this.facingDirection = facingDirection;
    }


    @Override
    public void apply(Robot robot) {
        robot.place(coordinates, facingDirection);
    }
}
