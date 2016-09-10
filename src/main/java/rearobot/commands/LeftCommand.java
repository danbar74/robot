package rearobot.commands;

import rearobot.robot.Robot;

class LeftCommand implements Command {
    @Override
    public void apply(Robot robot) {
        robot.left();
    }
}
