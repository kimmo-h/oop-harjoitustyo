import java.io.Serializable;
import java.util.ArrayList;

/**
 * Abstract class for all kinds of players.
 */
public abstract class Player implements Serializable {
    protected final String name;
    protected int[][] grid;
    protected ArrayList<Ship> ships;
    protected boolean shipDestroyed;

    /**
     * Constructs a player object.
     * @param name player's name
     * @param gridSize size of the grid
     */
    public Player(String name, int gridSize) {
        this.name = name;
        grid = new int[gridSize][gridSize];
        ships = new ArrayList<Ship>();
        shipDestroyed = false;
    }

    /**
     * Returns opponent's ships left.
     * @return opponent's ships left
     */
    public int shipsLeft() {
        return ships.size();
    }

    /**
     * Returns a grid representing hits and misses.
     * @return a grid representing hits and misses
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * Adds a ship to an ArrayList representing opponents ships.
     * @param direction Ship direction.
     * @param row Row.
     * @param column Column.
     * @param length Ship length.
     */
    public void placeShip(Direction direction, int row, int column, int length) {
        ships.add(new Ship(direction, row, column, length));
    }

    /**
     * Returns the effect of the turn played.
     * @param row row to be shot
     * @param column column to be shot
     * @return the effect caused by the shot
     */
    public Effect shoot(int row, int column) {
        Effect effect = Effect.MISS;
        if (grid[row][column] == 0) {
            grid[row][column] = 1;
            for (int i = 0; i < ships.size(); i++) {
                if (checkHit(ships.get(i), row, column)) {
                    grid[row][column] = 2;
                    if (ships.get(i).getHits() == ships.get(i).getLength()) {
                        ships.remove(i);
                        i--;
                        effect = Effect.DESTROYED;
                    }
                    else {
                        effect = Effect.HIT;
                    }
                }
            }
        }
        return effect;
    }

    /**
     * Returns true if the shot hits the ship given as parameter.
     * @param ship A ship to check.
     * @param row Row of a shot.
     * @param column Column of a shot.
     * @return true if the shot hits the ship given as parameter
     */
    private boolean checkHit(Ship ship, int row, int column) {
        if ((ship.getDirection() == Direction.HORIZONTAL && row == ship.getRow() && column >= ship.getColumn() && column <= ship.getColumn() + ship.getLength() - 1) ||
                (ship.getDirection() == Direction.VERTICAL && column == ship.getColumn() && row >= ship.getRow() && row <= ship.getRow() + ship.getLength() - 1)) {
            ship.setHits(ship.getHits() + 1);
            return true;
        }
        return false;
    }
}