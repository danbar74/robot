package rearobot.commands;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static rearobot.commands.CommandDefinitionParser.parse;

public class CommandDefinitionParserTest {
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenDefinitionIsNull()
    throws Exception {
        parse(null);
    }

    @Test(expected = InvalidCommandException.class)
    public void shouldThrowExceptionWhenCommandIsEmpty()
    throws Exception {
        parse("");
    }

    @Test(expected = InvalidCommandException.class)
    public void shouldThrowExceptionWhenCommandIsUnknown()
    throws Exception {
        parse("JUMP");
    }

    @Test(expected = InvalidCommandException.class)
    public void shouldThrowExceptionWhenPlaceCommandHasInvalidParameters()
    throws Exception {
        parse("PLACE 1,2,4,WEST");
    }

    @Test
    public void shouldParseAllSupportedCommands()
    throws Exception {
        assertThat(parse("PLACE 99,200,EAST"), instanceOf(PlaceCommand.class));
        assertThat(parse("MOVE"), instanceOf(MoveCommand.class));
        assertThat(parse("LEFT"), instanceOf(LeftCommand.class));
        assertThat(parse("RIGHT"), instanceOf(RightCommand.class));
        assertThat(parse("REPORT"), instanceOf(ReportCommand.class));
    }
}
