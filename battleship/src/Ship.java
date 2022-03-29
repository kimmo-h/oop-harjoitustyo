import java.io.Serializable;

/**
 * Represents a ship.
 */
public class Ship implements Serializable {
    private final Direction direction;
    private final int row;
    private final int column;
    private final int length;
    private int hits;

    /**
     * Constructs a ship.
     * @param direction Ship direction.
     * @param row Ship row.
     * @param column Ship column.
     * @param length Ship length.
     */
    public Ship(Direction direction, int row, int column, int length) {
        this.direction = direction;
        this.row = row;
        this.column = column;
        this.length = length;
        this.hits = 0;
    }

    /**
     * Returns the ship's direction.
     * @return the ship's direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Returns the ship's row.
     * @return the ship's row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the ship's column.
     * @return the ship's column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns the ship's length.
     * @return the ship's length
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the amount of hits the ship has taken.
     * @return the amount of hits the ship has taken
     */
    public int getHits() {
        return hits;
    }

    /**
     * Sets the amount of hits the ship has taken.
     * @param hits the amount of hits the ship has taken
     */
    public void setHits(int hits) {
        this.hits = hits;
    }
}