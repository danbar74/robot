package rearobot.table;

public enum Direction {
    NORTH, EAST, SOUTH, WEST;

    public Direction rotateRight() {
        switch (this) {
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            default: throw new IllegalStateException("Unknown orientation: " + this);
        }
    }

    public Direction rotateLeft() {
        switch (this) {
            case NORTH: return WEST;
            case EAST: return NORTH;
            case SOUTH: return EAST;
            case WEST: return SOUTH;
            default: throw new IllegalStateException("Unknown orientation: " + this);
        }
    }
}
