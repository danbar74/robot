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
    private TestReportWriter reportWriter;

    @Before
    public void before() {
        reportWriter = new TestReportWriter();
        robot = new Robot(new Table(5, 5), reportWriter);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingRobotWithNullTable() {
        new Robot(null, report -> {});
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCreatingRobotWithNullReportWriter() {
        new Robot(new Table(5, 5), null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenPlacingWithNullOrientation() {
        robot.place(new Coordinates(1, 1), null);
    }

    @Test
    public void shouldNotPlaceWhenCoordinatesDoNotExistOnTheTable() {
        assertThat(robot.place(new Coordinates(10, 3), NORTH), is(false));
        robot.report();
        assertThat(reportWriter.mostRecentReport(), is(REPORT_ROBOT_NOT_PLACED));
    }

    @Test
    public void shouldPlace() {
        assertThat(robot.place(new Coordinates(2, 4), NORTH), is(true));
        robot.report();
        assertThat(reportWriter.mostRecentReport(), is(not(REPORT_ROBOT_NOT_PLACED)));
    }

    @Test
    public void shouldNotMoveWhenNotPlaced() {
        assertThat(robot.move(), is(false));
    }

    @Test
    public void shouldNotMoveToPreventFallingOffTheTable() {
        robot.place(new Coordinates(5, 5), NORTH);
        robot.report();
        String reportBeforeMoving = reportWriter.mostRecentReport();
        assertThat(robot.move(), is(false));
        robot.report();
        assertThat(reportWriter.mostRecentReport(), is(reportBeforeMoving));
    }

    @Test
    public void shouldMove() {
        robot.place(new Coordinates(1, 1), EAST);
        assertThat(robot.move(), is(true));
        robot.report();
        assertThat(reportWriter.mostRecentReport(), is("2,1,EAST"));
    }

    @Test
    public void shouldNotRotateLeftWhenNotPlaced() {
        assertThat(robot.left(), is(false));
        robot.report();
        assertThat(reportWriter.mostRecentReport(), is(REPORT_ROBOT_NOT_PLACED));
    }

    @Test
    public void shouldRotateLeft() {
        robot.place(new Coordinates(2, 3), NORTH);
        assertThat(robot.left(), is(true));
        robot.report();
        assertThat(reportWriter.mostRecentReport(), is("2,3,WEST"));
    }

    @Test
    public void shouldNotRotateRightWhenNotPlaced() {
        assertThat(robot.right(), is(false));
        robot.report();
        assertThat(reportWriter.mostRecentReport(), is(REPORT_ROBOT_NOT_PLACED));
    }

    @Test
    public void shouldRotateRight() {
        robot.place(new Coordinates(2, 3), SOUTH);
        assertThat(robot.right(), is(true));
        robot.report();
        assertThat(reportWriter.mostRecentReport(), is("2,3,WEST"));
    }

    @Test
    public void shouldReportNotPlacedOnTheTableYetWhenNotPlaced() {
        robot.report();
        assertThat(reportWriter.mostRecentReport(), is(REPORT_ROBOT_NOT_PLACED));
    }

    @Test
    public void shouldReportCurrentCoordinatesAndFacingDirection() {
        robot.place(new Coordinates(1, 2), WEST);
        robot.report();
        assertThat(reportWriter.mostRecentReport(), is("1,2,WEST"));
    }
}
