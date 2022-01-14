package me.riskrider.mygame.tools;

import java.util.ArrayList;

public class SudokuPuzzle {

    public static final int INCOMPLETE = 0;
    public static final int INCORRECT = 1;
    public static final int CORRECT = 2;

    private int[][] board; // The puzzle board, containing the empty blocks.
    private int[][] solution; // The full solution board.
    private boolean[][] editable; // Array to see whether a block in 'board' is editable.

    /**
     * Constructs a random sudoku puzzle with a difficulty determined by diff.
     * @param diff Difficulty: 0 for easy, 1 for medium, 2 for hard, 3 or more for expert.
     */
    public SudokuPuzzle(int diff) {
        board = new int[9][9];
        editable = new boolean[9][9];
        newBoard(diff);
    }

    public void newBoard(int diff) {
        SudokuGen gen = new SudokuGen(diff);
        board = gen.getBoard();
        editable = gen.getEditable();
        solution = gen.getSolution();
    }

    public void clearBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if(editable[i][j]) board[i][j] = 0;
            }
        }
    }

    /**
     * Inserts a digit in a given block if the block is editable.
     * @param i Row co-ord.
     * @param j Column co-ord.
     * @param num Number to be inserted.
     */
    public void insertDigit(int i, int j, int num) {
        if (editable[i][j]) {
            if (num >= 0 && num < 10) {board[i][j] = num;}
        }
    }

    /**
     * Erases digit from position (i, j) of the board, if that digit may be erased.
     * @param i Row co-ord.
     * @param j Column co-ord.
     */
    public void eraseDigit(int i, int j) {
        if (editable[i][j]) {board[i][j] = 0;}
    }

    /**
     * Gets the generated puzzle.
     * @return The puzzle generated.
     */
    public int[][] getBoard() {return board;}

    /**
     * Gets the solution to the generated puzzle.
     * @return The solution.
     */
    public int[][] getSolution() {return solution;}

    /**
     * Checks if Sudoku puzzle is correct.
     * @return 0 if the puzzle is incomplete, 1 if it is complete but incorrect, 2 if it is complete and correct.
     */
    public int isComplete() {

        // Incomplete
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {return INCORRECT;}
            }
        }

        // Complete but incorrect
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != solution[i][j]) {return INCOMPLETE;}
            }
        }

        // Complete and correct
        return CORRECT;
    }

    /**
     * Checks if a digit may be inserted in a given block.
     * @param row Row co-ord.
     * @param col Column co-ord.
     * @param num Number to be inserted
     * @return True if the number may be inserted, false otherwise.
     */
    public boolean isSafe(int row, int col, int num) {
        for (int d = 0; d < board.length; d++)
        {
            if (board[row][d] == num) {
                return false;
            }
        }

        for (int r = 0; r < board.length; r++)
        {
            if (board[r][col] == num)
            {
                return false;
            }
        }

        int sqrt = (int)Math.sqrt(board.length);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;

        for (int r = boxRowStart;
             r < boxRowStart + sqrt; r++)
        {
            for (int d = boxColStart;
                 d < boxColStart + sqrt; d++)
            {
                if (board[r][d] == num)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public void getHint () {

        ArrayList<Integer[]> available = new ArrayList<Integer[]>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (editable[i][j] &&  board[i][j] == 0) {

                    Integer[] add = new Integer[2];
                    add[0] = i;
                    add[1] = j;
                    available.add(add);

                }

            }
        }

        Dice dice = new Dice(available.size());
        int chosen = dice.roll() - 1;

        Integer[] coord = available.get(chosen);

        board[coord[0]][coord[1]] = solution[coord[0]][coord[1]];

    }

    public String getDigit(int i, int j) {
        switch (board[i][j]) {
            case 1: return "1";
            case 2: return "2";
            case 3: return "3";
            case 4: return "4";
            case 5: return "5";
            case 6: return "6";
            case 7: return "7";
            case 8: return "8";
            case 9: return "9";
        }

        return " ";
    }

    public boolean[][] getEditable() {
        return editable;
    }
}