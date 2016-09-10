package rearobot.table;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TableTest {
    @Test
    public void shouldCreateTableTop() {
        new Table(3, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCreatingTableTopWithInvalidSizeX() {
        new Table(0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenCreatingTableTopWithInvalidSizeY() {
        new Table(10, -2);
    }

    @Test
    public void shouldContainCoordinatesDefinedByDimensions() {
        Table tableTop = new Table(5, 5);
        assertThat(tableTop.contains(new Coordinates(1, 1)), is(true));
        assertThat(tableTop.contains(new Coordinates(5, 5)), is(true));
        assertThat(tableTop.contains(new Coordinates(1, 5)), is(true));
        assertThat(tableTop.contains(new Coordinates(5, 1)), is(true));
        assertThat(tableTop.contains(new Coordinates(2, 3)), is(true));
    }

    @Test
    public void shouldNotContainCoordinatesNotDefinedByDimensions() {
        Table tableTop = new Table(3, 6);
        assertThat(tableTop.contains(new Coordinates(1, 7)), is(false));
        assertThat(tableTop.contains(new Coordinates(6, 3)), is(false));
        assertThat(tableTop.contains(new Coordinates(0, 0)), is(false));
        assertThat(tableTop.contains(new Coordinates(-1, 2)), is(false));
        assertThat(tableTop.contains(new Coordinates(2, -1)), is(false));
    }
}
