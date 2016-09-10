package rearobot.table;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.MatcherAssert.assertThat;
import static rearobot.table.Direction.EAST;
import static rearobot.table.Direction.NORTH;
import static rearobot.table.Direction.SOUTH;
import static rearobot.table.Direction.WEST;

public class CoordinatesTest {
    private Coordinates coordinates;

    @Before
    public void before() {
        coordinates = new Coordinates(3, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAdjustingWithNegativeNumberOfUnits() {
        coordinates.adjust(-1, NORTH);
    }

    @Test
    public void shouldReturnItselfWhenAdjustingByZeroUnits() {
        Stream.of(Direction.values()).forEach(direction -> {
            Coordinates newCoordinates = coordinates.adjust(0, direction);
            assertThat(newCoordinates, sameInstance(coordinates));
        });
    }

    @Test
    public void shouldAdjustWhenMovingNorth() {
        Coordinates newCoordinates = coordinates.adjust(3, NORTH);
        assertThat(newCoordinates.getX(), is(3));
        assertThat(newCoordinates.getY(), is(10));
    }

    @Test
    public void shouldAdjustWhenMovingSouth() {
        Coordinates newCoordinates = coordinates.adjust(10, SOUTH);
        assertThat(newCoordinates.getX(), is(3));
        assertThat(newCoordinates.getY(), is(-3));
    }

    @Test
    public void shouldAdjustWhenMovingEast() {
        Coordinates newCoordinates = coordinates.adjust(3, EAST);
        assertThat(newCoordinates.getX(), is(6));
        assertThat(newCoordinates.getY(), is(7));
    }

    @Test
    public void shouldAdjustWhenMovingWest() {
        Coordinates newCoordinates = coordinates.adjust(6, WEST);
        assertThat(newCoordinates.getX(), is(-3));
        assertThat(newCoordinates.getY(), is(7));
    }
}
