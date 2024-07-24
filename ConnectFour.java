import java.util.Scanner;

public class ConnectFour {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private static final char EMPTY = ' ';
    private static final char PLAYER1 = 'X';
    private static final char PLAYER2 = 'O';
    private static char[][] board = new char[ROWS][COLUMNS];

    public static void main(String[] args) {
        initializeBoard();
        displayBoard();

        char currentPlayer = PLAYER1;

        boolean gameFinished = false;
        while (!gameFinished) {
            int column = getColumn(currentPlayer);
            int row = dropDisc(column, currentPlayer);
            displayBoard();

            if (checkWin(row, column, currentPlayer)) {
                System.out.println("Player " + currentPlayer + " wins!");
                gameFinished = true;
            } else if (isBoardFull()) {
                System.out.println("It's a draw!");
                gameFinished = true;
            }

            currentPlayer = (currentPlayer == PLAYER1) ? PLAYER2 : PLAYER1;
        }
    }

    private static void initializeBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    private static void displayBoard() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                System.out.print("|" + board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println("---------------");
    }

    private static int getColumn(char currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        int column;
        do {
            System.out.println("Player " + currentPlayer + ", enter column (1-" + COLUMNS + "): ");
            column = scanner.nextInt() - 1;
        } while (column < 0 || column >= COLUMNS || board[0][column] != EMPTY);
        return column;
    }

    private static int dropDisc(int column, char currentPlayer) {
        int row = ROWS - 1;
        while (board[row][column] != EMPTY) {
            row--;
        }
        board[row][column] = currentPlayer;
        return row;
    }

    private static boolean checkWin(int row, int col, char currentPlayer) {
        // Check horizontally
        int count = 0;
        for (int i = Math.max(0, col - 3); i <= Math.min(col + 3, COLUMNS - 1); i++) {
            if (board[row][i] == currentPlayer) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }

        // Check vertically
        count = 0;
        for (int i = Math.max(0, row - 3); i <= Math.min(row + 3, ROWS - 1); i++) {
            if (board[i][col] == currentPlayer) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }

        // Check diagonally
        count = 0;
        for (int i = -3; i <= 3; i++) {
            int r = row + i;
            int c = col + i;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && board[r][c] == currentPlayer) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }

        count = 0;
        for (int i = -3; i <= 3; i++) {
            int r = row + i;
            int c = col - i;
            if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && board[r][c] == currentPlayer) {
                count++;
                if (count == 4) return true;
            } else {
                count = 0;
            }
        }

        return false;
    }

    private static boolean isBoardFull() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}