import java.util.Scanner;

/**
 * This class provides methods for visualizing the game and showing dialogs.
 */
public class GameUI {
    Scanner inputScanner;

    /**
     * Constructor. Initializes Scanner object for user input.
     */
    public GameUI() {
        inputScanner = new Scanner(System.in);
    }

    /**
     * Prints a grid.
     * @param grid grid to be printed
     */
    public void printGrid(int[][] grid) {
        System.out.print(" ");
        for (int i = 0; i < grid.length; i++) {
            System.out.print("  " + i);
        }
        System.out.println();
        for (int i = 0; i < grid.length; i++) {
            System.out.print(i);
            for (int j = 0; j < grid.length; j++) {
                switch (grid[i][j]) {
                    case 0 -> System.out.print("  +");
                    case 1 -> System.out.print("  O");
                    case 2 -> System.out.print("  X");
                }
            }
            System.out.println();
        }
    }

    /**
     * Clears console window.
     */
    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Method for creating dialogs. Checks if user input is valid and returns it.
     * @param message message to display
     * @param errorMessage message to display if the input is out of bounds
     * @param minInput minimum value accepted as input
     * @param maxInput maximum value accepted as input
     * @return the entered value
     */
    private int dialog(String message, String errorMessage, int minInput, int maxInput) {
        int input;

        while (true) {
            System.out.println(message);
            try {
                input = Integer.parseInt(inputScanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Incorrect input!");
                continue;
            }
            if (input >= minInput && input <= maxInput) {
                break;
            }
            System.out.println(errorMessage);
        }
        return input;
    }

    /**
     * Shows a start dialog.
     * @return 1 if user wants to start a new game, 2 if user wants to load a saved game
     */
    public int startDialog() {
        return dialog("1: Start new game\n2: Load game", "Input out of bounds!", 1,2);
    }

    /**
     * Shows a save dialog.
     * @return 1 if user wants to continue playing, 2 if user wants to save and exit the game
     */
    public boolean saveDialog() {
        return dialog("1: Continue\n2: Save and exit", "Input out of bounds!", 1, 2) == 2;
    }

    /**
     * Shows a dialog asking for a ship direction.
     * @return the direction in which the ship is placed
     */
    public Direction askDirection() {
        if (dialog("Give Direction:\n1: horizontal\n2: vertical", "Input out of bounds!", 1, 2) == 1) {
            return Direction.HORIZONTAL;
        }
        return Direction.VERTICAL;
    }

    /**
     * Shows a dialog asking for a row where to place a ship.
     * @return the row in which the ship is placed
     */
    public int placeRow(int maxRow) {
        return dialog("Give row (0-" + maxRow + ")", "Ship does not fit!",0, maxRow);
    }

    /**
     * Shows a dialog asking for a column where to place a ship.
     * @return the column in which the ship is placed
     */
    public int placeColumn(int maxColumn) {
        return dialog("Give column (0-" + maxColumn + ")", "Ship does not fit!",0, maxColumn);
    }

    /**
     * Shows a dialog asking for a row where to shoot.
     * @return the row to be shot
     */
    public int shootRow(int maxRow) {
        return dialog("Give row (0-9)", "Out of grid! Try again.",0, maxRow);
    }

    /**
     * Shows a dialog asking for a column where to shoot.
     * @return the column to be shot
     */
    public int shootColumn(int maxColumn) {
        return dialog("Give column (0-9)", "Out of grid! Try again.",0, maxColumn);
    }

    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        inputScanner.close();
    }
}