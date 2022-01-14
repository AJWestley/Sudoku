package me.riskrider.mygame.tools;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BoardDisplay {

    private SudokuPuzzle puzzle;
    public String[][] display;

    public BoardDisplay (SudokuPuzzle puzzle) {
        this.puzzle = puzzle;
        display = new  String[9][9];
        update();
    }

    public void update () {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                display[i][j] = puzzle.getDigit(i, j);

            }
        }
    }

    public void render (SpriteBatch batch, BitmapFont font, int[] xCoords, int[] yCoords) {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                font.draw(batch, display[i][j], xCoords[j], yCoords[i]);

            }
        }

    }
}
