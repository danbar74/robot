package rearobot.commands;

import rearobot.robot.Robot;

class MoveCommand implements Command {
    @Override
    public void apply(Robot robot) {
        robot.move();
    }
}
