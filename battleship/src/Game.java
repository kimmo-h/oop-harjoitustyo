import java.io.IOException;

/**
 * Main class of the battleship game.
 */
public class Game {
    private final int gridSize;
    private final int numberOfShips;
    private final GameUI ui;
    private final ObjectSaverLoader saverLoader;
    private Player player1;
    private Player player2;

    /**
     * Constructs the game.
     */
    public Game() {
        gridSize = 10;
        //Max. number of ships is gridSize / 2
        numberOfShips = 3;
        ui = new GameUI();
        saverLoader = new ObjectSaverLoader();
        player1 = new HumanPlayer("Player 1", gridSize);
        player2 = new HumanPlayer("Player 2", gridSize);
    }

    /**
     * Starts the game.
     */
    public void play() {
        switch (ui.startDialog()) {
            case 1 -> createGame();
            case 2 -> {
                if(!loadGame()) {
                    return;
                }
            }
        }
        gameLoop();
        ui.closeScanner();
    }

    /**
     * Creates a new game.
     */
    private void createGame() {
        System.out.println("Player 1 place your ships!");
        placeShips(player2);
        ui.clear();
        System.out.println("Player 2 place your ships!");
        placeShips(player1);
        ui.clear();
    }

    /**
     * Loads a saved game. Returns true if loading succeeds.
     * @return true if loading succeeds
     */
    private boolean loadGame() {
        try {
            ObjectCombiner players = (ObjectCombiner) saverLoader.loadObjectFromFile("save.ser");
            player1 = (Player) players.getObject1();
            player2 = (Player) players.getObject2();
            return true;
        }
        catch (Exception e) {
            System.out.println("No save file or file corrupted");
            return false;
        }
    }

    /**
     * Combines Player objects into a single object and saves it into a file.
     */
    private void saveGame() {
        try {
            saverLoader.saveObjectToFile(new ObjectCombiner(player1, player2), "save.ser");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the game loop.
     */
    private void gameLoop() {
        while (true) {
            if (ui.saveDialog()) {
                saveGame();
                break;
            }
            ui.clear();
            if (playTurn(player1)) {
                System.out.println("Player 1 wins!");
                break;
            }
            if (playTurn(player2)) {
                System.out.println("Player 2 wins");
                break;
            }
        }
    }

    /**
     * Place ships to a grid.
     * @param player player whose opponents turn is to place the ships
     */
    private void placeShips(Player player) {
        for (int i = 0; i < numberOfShips; i++) {
            int maxRow = gridSize - 1;
            int maxColumn = gridSize - 1;
            int length = gridSize / 2 - i;
            Direction direction = ui.askDirection();
            if (direction == Direction.HORIZONTAL) {
                maxColumn = gridSize - length;
            }
            else {
                maxRow = gridSize - length;
            }
            int row = ui.placeRow(maxRow);
            int column = ui.placeColumn(maxColumn);
            player.placeShip(direction, row, column, length);
        }
    }

    /**
     * Play a turn. Returns true if player wins.
     * @param player player whose turn is played
     */
    private boolean playTurn(Player player) {
        int maxRow = gridSize - 1;
        int maxColumn = gridSize - 1;
        int[][] grid = player.getGrid();
        ui.printGrid(grid);
        switch (player.shoot(ui.shootRow(maxRow), ui.shootColumn(maxColumn))) {
            case MISS -> System.out.println("MISS!");
            case HIT -> System.out.println("HIT!");
            case DESTROYED -> System.out.println("ENEMY SHIP DESTROYED!");
        }
        return player.shipsLeft() == 0;
    }
}