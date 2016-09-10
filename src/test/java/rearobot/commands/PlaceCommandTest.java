package rearobot.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rearobot.robot.Robot;
import rearobot.table.Coordinates;
import rearobot.table.Direction;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static rearobot.table.Direction.WEST;

@RunWith(MockitoJUnitRunner.class)
public class PlaceCommandTest {
    @Mock Robot robot;

    @Test
    public void shouldApplyToRobot() {
        Coordinates coordinates = new Coordinates(4, 7);
        Direction facingDirection = WEST;
        Command command = new PlaceCommand(coordinates, facingDirection);
        command.apply(robot);
        verify(robot).place(eq(coordinates), eq(facingDirection));
    }
}
