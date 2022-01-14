package me.riskrider.mygame.tools;

public class SudokuGen {

    private int difficulty; // The difficulty of the puzzle being generated.
    private int[][] board; // The puzzle board, containing the empty blocks.
    private int[][] solution; // The full solution board.
    private boolean[][] editable; // Array to see whether a block in 'board' is editable.

    /**
     * Generates a random sudoku puzzle of difficulty diff.
     * @param diff Difficulty: 0 for easy, 1 for medium, 2 for hard, 3 or more for expert.
     */
    public SudokuGen(int diff) {
        difficulty = diff;
        solution = new int[9][9];
        board = new int[9][9];
        editable = new boolean[9][9];
        genPuzzle();
    }

    /**
     * Generates a full board and allocates it to the solution and board arrays.
     */
    private void genFullBoard() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                editable[i][j] = false;
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solution[i][j] = 0;
            }
        }

        Solvable.genSuduko(solution, 0, 0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {board[i][j] = solution[i][j];}
        }
    }

    /**
     * Removes digits from the board array to create a puzzle with a unique solution, keeping the solution array in tact.
     */
    private void genPuzzle() {
        genFullBoard();
        Dice rand = new Dice(0, 8);
        int count = 0;
        int upTo = 40 + (difficulty * 5);
        int failCount = 0;
        while (count < upTo) {
            int i = rand.roll();
            int j = rand.roll();
            if (!editable[i][j]) {
                int temp = board[i][j];
                board[i][j] = 0;
                editable[i][j] = true;
                count++;
                if (Solvable.solve(i, j, board, 0) != 1) {
                    count--;
                    board[i][j] = temp;
                    editable[i][j] = false;
                    failCount++;
                }
                else
                    failCount = 0;
            }
            if (failCount > 1000)
                break;
        }
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
     * Gets the array of which blocks are editable.
     * @return The array of which blocks are editable.
     */
    public boolean[][] getEditable() {return editable;}
}