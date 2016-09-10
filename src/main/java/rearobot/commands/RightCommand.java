package rearobot.commands;

import rearobot.robot.Robot;

class RightCommand implements Command {
    @Override
    public void apply(Robot robot) {
        robot.right();
    }
}
