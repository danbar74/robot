package rearobot.table;

import static rearobot.Preconditions.checkArgument;
import static rearobot.Preconditions.checkNotNull;

public class Table {
    private final int sizeX;
    private final int sizeY;

    /**
     * Creates a table with with coordinate space, where south-west is {@code [0, 0]}
     * and north-east is {@code [sizeX - 1, sizeY - 1]}.
     *
     * @param sizeX vertical size
     * @param sizeY horizontal size
     */
    public Table(int sizeX, int sizeY) {
        checkArgument(sizeX > 0, "size X must not be equal or less than 0");
        checkArgument(sizeY > 0, "size Y must not be equal or less than 0");
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    /**
     * Veirifies if specified <code>coordinates</code> exist on this table.
     *
     * @throws NullPointerException <code>coordinates</code> is <code>NULL</code>
     */
    public boolean contains(Coordinates coordinates) {
        checkNotNull(coordinates, "coordinates must not be NULL");
        return coordinates.getX() >= 0 && coordinates.getX() < sizeX
                && coordinates.getY() >= 0 && coordinates.getY() < sizeY;
    }

    @Override
    public String toString() {
        return "(" + sizeX + " x " + sizeY + ")";
    }
}
