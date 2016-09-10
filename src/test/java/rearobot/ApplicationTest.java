package rearobot;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import rearobot.robot.Robot;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {
    @Rule public TemporaryFolder temp = new TemporaryFolder();

    private Application application;
    @Mock private Robot robot;

    @Before
    public void before() {
        application = new Application();
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenInputFilePathIsNull()
    throws Exception {
        application.runSimulation(null, robot);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionRobotIsNull()
    throws Exception {
        application.runSimulation("test", null);
    }

    @Test(expected = ApplicationException.class)
    public void shouldThrowExceptionWhenInputFileDoesNotExist()
    throws Exception {
        application.runSimulation("now/way/this/file/exists", robot);
    }

    @Test
    public void shouldRunSimulationSkippingInvalidCommands()
    throws Exception {
        List<String> commands = new ArrayList<>();
        commands.add("PLACE 1,2,NORTH");
        commands.add("MOVE");
        commands.add("JUMP");
        commands.add("RIGHT");
        commands.add("JUMP");
        commands.add("MOVE");
        commands.add("REPORT");
        File inputFile = temp.newFile("text.txt");
        Files.write(inputFile.toPath(), commands);
        application.runSimulation(inputFile.getAbsolutePath(), robot);
        InOrder inOrder = inOrder(robot);
        inOrder.verify(robot).place(any(), any());
        inOrder.verify(robot).move();
        inOrder.verify(robot).right();
        inOrder.verify(robot).move();
        inOrder.verify(robot).report();
        verifyNoMoreInteractions(robot);
    }
}
