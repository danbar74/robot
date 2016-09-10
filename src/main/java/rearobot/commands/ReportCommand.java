package rearobot.commands;

import rearobot.robot.Robot;

class ReportCommand implements Command {
    @Override
    public void apply(Robot robot) {
        robot.report();
    }
}
