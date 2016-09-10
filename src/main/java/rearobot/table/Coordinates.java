package rearobot.table;

import static rearobot.Preconditions.checkArgument;
import static rearobot.Preconditions.checkNotNull;

public class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Calculates new coordinates based on this coordinates, specified {@code distance} and {@code direction}.
     *
     * @return new coordinates
     *
     * @throws NullPointerException {@code direction} is NULL
     * @throws IllegalArgumentException {@code distance} is negative or unknown {@code direction} has been specified
     */
    public Coordinates adjust(int distance, Direction direction) {
        checkArgument(distance >= 0, "distance must not be negative");
        checkNotNull(direction, "direction must not be null");
        if (distance == 0) {
            return this;
        }
        switch (direction) {
            case NORTH:
                return new Coordinates(x, y + distance);
            case SOUTH:
                return new Coordinates(x, y - distance);
            case WEST:
                return new Coordinates(x - distance, y);
            case EAST:
                return new Coordinates(x + distance, y);
            default:
                throw new IllegalArgumentException("unknown orientation: " + direction);
        }
    }
}
