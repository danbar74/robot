package rearobot.robot;

import rearobot.table.Coordinates;
import rearobot.table.Direction;
import rearobot.table.Table;

import static rearobot.Preconditions.checkNotNull;

public class Robot {
    private final Table table;
    private Coordinates coordinates;
    private Direction facingDirection;

    /**
     * Creates a robot.
     *
     * @param table the table this robot can move around
     *
     * @throws NullPointerException {@code table} is NULL
     */
    public Robot(Table table) {
        this.table = checkNotNull(table, "Specified table is NULL");
    }

    /**
     * Places the robot on the table.
     *
     * @param coordinates new coordinates
     * @param facingDirection new facing direction
     *
     * @return {@code true} if the robot has been placed on the table successfully,
     * {@code false} otherwise ({@code coordinates} do not exist on the table)
     *
     * @throws NullPointerException <code>table</code> is NULL
     */
    public boolean place(Coordinates coordinates, Direction facingDirection) {
        checkNotNull(coordinates, "coordinates must not be null");
        checkNotNull(facingDirection, "facing direction must not be null");
        if (table.contains(coordinates)) {
            this.coordinates = coordinates;
            this.facingDirection = facingDirection;
            return true;
        }
        return false;
    }

    /**
     * Moves the robot one unit forward.
     * Does nothing, if the the robot has not been placed on the table yet.
     *
     * @return {@code true} if robot has been moved successfully, {@code false} otherwise.
     *
     * @see Robot#place(Coordinates, Direction)
     */
    public boolean move() {
        if (placedOnTheTable()) {
            Coordinates newCoordinates = coordinates.adjust(1, facingDirection);
            if (table.contains(newCoordinates)) {
                coordinates = newCoordinates;
                return true;
            }
        }
        return false;
    }

    /**
     * Rotates the robot left by 90 degress.
     * Does nothing, if the the robot has not been placed on the table yet.
     *
     * @return {@code true} if robot has been rotated successfully, {@code false} otherwise.
     *
     * @see Robot#place(Coordinates, Direction)
     */
    public boolean left() {
        if (placedOnTheTable()) {
            facingDirection = facingDirection.rotateLeft();
            return true;
        }
        return false;
    }

    /**
     * Rotates the robot right by 90 degress.
     * Does nothing, if the the robot has not been placed on the table yet.
     *
     * @return {@code true} if robot has been rotated successfully, {@code false} otherwise.
     *
     * @see Robot#place(Coordinates, Direction)
     */
    public boolean right() {
        if (placedOnTheTable()) {
            facingDirection = facingDirection.rotateRight();
            return true;
        }
        return false;
    }

    /**
     * Reports current robot coordinates and facing direction.
     *
     * @return coordinates and facing direction formatted as {@code "X,Y,DIRECTION"} (i.e. {@code "3,10,EAST"})
     * if the robot is on the table, {@code "Not placed on the table yet"} otherwise
     */
    public String report() {
        return placedOnTheTable()
                ? String.format("%d,%d,%s", coordinates.getX(), coordinates.getY(), facingDirection)
                : "Not placed on the table yet";
    }

    private boolean placedOnTheTable() {
        return coordinates != null && facingDirection != null;
    }
}
