package rearobot.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rearobot.robot.Robot;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LeftCommandTest {
    @Mock Robot robot;

    @Test
    public void shouldApplyToRobot() {
        Command command = new LeftCommand();
        command.apply(robot);
        verify(robot).left();
    }
}
