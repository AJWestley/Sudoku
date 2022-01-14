package me.riskrider.mygame.tools;

import java.util.ArrayList;
import java.util.Collections;

public class Solvable {

    /**
     * Checks if a digit may be inserted in a given block.
     * @param row Row co-ord.
     * @param col Column co-ord.
     * @param num Number to be inserted
     * @return True if the number may be inserted, false otherwise.
     */
    public static boolean isSafe(int[][] board, int row, int col, int num)
    {

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

    /**
     * Checks if there is 0, 1 or >1 solutions to a Sudoku puzzle.
     * @param i Default co-ord to 0, only used for recursive purposes.
     * @param j Default co-ord to 0, only used for recursive purposes.
     * @param cells The 9x9 array to be checked.
     * @param count Default solution count to 0, only used for recursive purposes.
     * @return The number of solutions a puzzle has.
     */
    static int solve(int i, int j, int[][] cells, int count) {
        if (i == 9) {
            i = 0;
            if (++j == 9)
                return 1+count;
        }
        if (cells[i][j] != 0)
            return solve(i+1,j,cells, count);

        for (int val = 1; val <= 9 && count < 2; ++val) {
            if (isSafe(cells, i, j, val)) {
                cells[i][j] = val;
                count = solve(i+1,j,cells, count);
            }
        }
        cells[i][j] = 0; // reset on backtrack
        return count;
    }

    /**
     * Uses sudoku solver to randomly generate sudoku board.
     * @param grid 9x9 puzzle to "solve".
     * @param row Default co-ord to 0, only used for recursive purposes.
     * @param col Default co-ord to 0, only used for recursive purposes.
     * @return True if board is created, only used for recursive purposes.
     */
    static boolean genSuduko(int grid[][], int row, int col)
    {
        ArrayList<Integer> randList = new ArrayList<Integer>();
        for (int a = 1; a <= 9; a++) {randList.add(a);}
        Collections.shuffle(randList);

        if (row == 9 - 1 && col == 9)
            return true;

        if (col == 9) {
            row++;
            col = 0;
        }

        if (grid[row][col] != 0)
            return genSuduko(grid, row, col + 1);

        for (int num = 0; num < 9; num++) {

            int ins = randList.get(num);

            if (isSafe(grid, row, col, ins)) {

                grid[row][col] = ins;

                if (genSuduko(grid, row, col + 1))
                    return true;
            }

            grid[row][col] = 0;
        }
        return false;
    }
}

