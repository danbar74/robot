package rearobot.robot;

import org.junit.Before;
import org.junit.Test;
import rearobot.table.Coordinates;
import rearobot.table.Table;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static rearobot.table.Direction.EAST;
import static rearobot.table.Direction.NORTH;
import static rearobot.table.Direction.SOUTH;
import static rearobot.table.Direction.WEST;

public class RobotTest {
    private static final String REPORT_ROBOT_NOT_PLACED = "Not placed on the table yet";

    private Robot robot;

    @Before
    public void before() {
        robot = new Robot(new Table(5, 5));
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingRobotWithNullTableTop() {
        new Robot(null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenPlacingWithNullOrientation() {
        robot.place(new Coordinates(1, 1), null);
    }

    @Test
    public void shouldNotPlaceWhenCoordinatesDoNotExistOnTheTable() {
        assertThat(robot.place(new Coordinates(10, 3), NORTH), is(false));
        assertThat(robot.report(), is(REPORT_ROBOT_NOT_PLACED));
    }

    @Test
    public void shouldPlace() {
        assertThat(robot.place(new Coordinates(3, 5), NORTH), is(true));
        assertThat(robot.report(), is(not(REPORT_ROBOT_NOT_PLACED)));
    }

    @Test
    public void shouldNotMoveWhenNotPlaced() {
        assertThat(robot.move(), is(false));
    }

    @Test
    public void shouldNotMoveToPreventFallingOffTheTable() {
        robot.place(new Coordinates(5, 5), NORTH);
        String reportBeforeMoving = robot.report();
        assertThat(robot.move(), is(false));
        assertThat(robot.report(), is(reportBeforeMoving));
    }

    @Test
    public void shouldMove() {
        robot.place(new Coordinates(1, 1), EAST);
        assertThat(robot.move(), is(true));
        assertThat(robot.report(), is("2,1,EAST"));
    }

    @Test
    public void shouldNotRotateLeftWhenNotPlaced() {
        assertThat(robot.left(), is(false));
        assertThat(robot.report(), is(REPORT_ROBOT_NOT_PLACED));
    }

    @Test
    public void shouldRotateLeft() {
        robot.place(new Coordinates(2, 3), NORTH);
        assertThat(robot.left(), is(true));
        assertThat(robot.report(), is("2,3,WEST"));
    }

    @Test
    public void shouldNotRotateRightWhenNotPlaced() {
        assertThat(robot.right(), is(false));
        assertThat(robot.report(), is(REPORT_ROBOT_NOT_PLACED));
    }

    @Test
    public void shouldRotateRight() {
        robot.place(new Coordinates(2, 3), SOUTH);
        assertThat(robot.right(), is(true));
        assertThat(robot.report(), is("2,3,WEST"));
    }

    @Test
    public void shouldReportNotPlacedOnTheTableYetWhenNotPlaced() {
        assertThat(robot.report(), is(REPORT_ROBOT_NOT_PLACED));
    }

    @Test
    public void shouldReportCurrentCoordinatesAndFacingDirection() {
        robot.place(new Coordinates(1, 2), WEST);
        assertThat(robot.report(), is("1,2,WEST"));
    }
}
