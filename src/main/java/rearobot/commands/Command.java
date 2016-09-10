package rearobot.commands;

import rearobot.robot.Robot;

public interface Command {
    void apply(Robot robot);
}
