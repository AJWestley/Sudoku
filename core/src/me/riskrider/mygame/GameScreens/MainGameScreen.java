package me.riskrider.mygame.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import me.riskrider.mygame.Sudoku;
import me.riskrider.mygame.tools.BoardDisplay;
import me.riskrider.mygame.tools.SudokuPuzzle;

public class MainGameScreen implements Screen {

    private final int gridDimensions = 600;
    private final int gridX = Sudoku.WIDTH / 2 - gridDimensions / 2;
    private final int gridY = Sudoku.HEIGHT / 3;
    private final int pauseDimensions = 70;
    private final int pauseX = 15 * Sudoku.WIDTH / 16 - pauseDimensions;
    private final int pauseY = 15 * Sudoku.HEIGHT / 16 - pauseDimensions + 10;
    private final int pauseMenuX = Sudoku.WIDTH / 2;
    private final int pauseMenuY = 400;
    private final int pauseMenuWidth = 960;
    private final int pauseMenuHeight = 540;
    private final int exitAMX = 7 * Sudoku.WIDTH / 8;
    private final int exitAMY = 480;
    private final int exitAMWidth = 75;
    private final int exitAMHeight = 75;
    private final int volumeX = (3 * Sudoku.WIDTH / 4) - 100;
    private final int volumeY = 600;
    private final int effectsY = 520;
    private final int volumeDimensions = 70;
    private final int volumeOffsetX = 80;
    private final int volumeOffsetY = 30;
    private final int effectsOffsetX = 50;
    private final int mainMenuX = Sudoku.WIDTH / 8;
    private final int mainMenuY = 780;
    private final int newPuzzleX = 7 * Sudoku.WIDTH / 8;
    private final int newPuzzleY = 780;
    private final int pauseCircleOffsetX = 20;
    private final int pauseCircleOffsetY = 25;
    private final int mainMenuCircleWidth = 260;
    private final int mainMenuCircleHeight = 80;
    private final int newPuzzleCircleWidth = 270;
    private final int newPuzzleCircleHeight = 80;
    private final int gameTimerX = Sudoku.WIDTH / 8;
    private final int gameTimerY = 15 * Sudoku.HEIGHT / 16;
    private final int selectDifficultyWidth = 592;
    private final int selectDifficultyHeight = 622;
    private final int selectDifficultyX = Sudoku.WIDTH / 2 - selectDifficultyWidth / 2;
    private final int selectDifficultyY = Sudoku.HEIGHT / 2 - selectDifficultyHeight / 2;
    private final int easyCircleWidth = 120;
    private final int easyCircleHeight = 80;
    private final int mediumCircleWidth = 170;
    private final int mediumCircleHeight = 80;
    private final int hardCircleWidth = 130;
    private final int hardCircleHeight = 80;
    private final int expertCircleWidth = 160;
    private final int expertCircleHeight = 80;
    private final int yesCircledHeight = 70;
    private final int yesCircledWidth = 90;
    private final int noCircledWidth = 80;
    private final int boxDimensions = 61;
    private final int boxCircledDimensions = 50;
    private final int resetX = Sudoku.WIDTH / 8;
    private final int resetY = gridY + gridDimensions - 10;
    private final int resetWidth = 120;
    private final int resetHeight = 50;
    private final int hintX = Sudoku.WIDTH / 2;
    private final int hintY = pauseY;
    private final int hintDimensions = 100;

    //row y co-ords
    private int[] rowYCoordinates = new int[9];

    //column x co-ords
    private int[] colXCoordinates = new int[9];

    boolean isPaused;
    boolean newPuzzleClicked;
    boolean mainMenuClicked;
    int difficulty;

    BitmapFont large;
    BitmapFont medium;
    BitmapFont small;
    Texture volumeFull;
    Texture volume75;
    Texture volumeHalf;
    Texture volume25;
    Texture volumeOff;
    Texture circled;
    Texture grid;
    Texture pauseMenu;
    Texture stickyNote;
    Texture pauseActive;
    Texture pauseInactive;
    Texture exitAMInactive;
    Texture exitAMActive;
    Texture dim;
    Texture hintInactive;
    Texture hintActive;
    Texture resetInactive;
    Texture resetActive;

    Sudoku game;
    SudokuPuzzle sudokuPuzzle;
    BoardDisplay boardDisplay;
    int rowSelected;
    int colSelected;

    public MainGameScreen (Sudoku game, int difficulty) {

        this.game = game;

        large = new BitmapFont(Gdx.files.internal("title/font.fnt"));
        large.setColor(Color.BLACK);
        medium = new BitmapFont(Gdx.files.internal("difficulties/font.fnt"));
        medium.setColor(Color.BLACK);
        small = new BitmapFont(Gdx.files.internal("exit/font.fnt"));
        small.setColor(Color.BLACK);

        this.difficulty = difficulty;

        sudokuPuzzle = new SudokuPuzzle(this.difficulty);

        boardDisplay = new BoardDisplay(sudokuPuzzle);

        rowYCoordinates[0] = gridY + gridDimensions - 37;
        rowYCoordinates[1] = rowYCoordinates[0] - boxDimensions;
        rowYCoordinates[2] = rowYCoordinates[1] - boxDimensions;
        rowYCoordinates[3] = rowYCoordinates[2] - boxDimensions;
        rowYCoordinates[4] = rowYCoordinates[3] - boxDimensions;
        rowYCoordinates[5] = rowYCoordinates[4] - boxDimensions;
        rowYCoordinates[6] = rowYCoordinates[5] - boxDimensions;
        rowYCoordinates[7] = rowYCoordinates[6] - boxDimensions;
        rowYCoordinates[8] = rowYCoordinates[7] - boxDimensions;

        colXCoordinates[0] = gridX + 45;
        colXCoordinates[1] = colXCoordinates[0] + boxDimensions;
        colXCoordinates[2] = colXCoordinates[1] + boxDimensions;
        colXCoordinates[3] = colXCoordinates[2] + boxDimensions;
        colXCoordinates[4] = colXCoordinates[3] + boxDimensions;
        colXCoordinates[5] = colXCoordinates[4] + boxDimensions;
        colXCoordinates[6] = colXCoordinates[5] + boxDimensions;
        colXCoordinates[7] = colXCoordinates[6] + boxDimensions;
        colXCoordinates[8] = colXCoordinates[7] + boxDimensions;

        colSelected = 9;
        rowSelected = 9;

    }

    @Override
    public void show() {

        grid = new Texture(Gdx.files.internal("grid.png"));
        pauseActive = new Texture(Gdx.files.internal("pauseActive.png"));
        pauseInactive = new Texture(Gdx.files.internal("pauseInactive.png"));
        pauseMenu = new Texture(Gdx.files.internal("audioMenu.png"));
        exitAMInactive = new Texture(Gdx.files.internal("exitAudoMenuInactive.png"));
        exitAMActive = new Texture(Gdx.files.internal("exitAudoMenuActive.png"));
        volumeFull = new Texture(Gdx.files.internal("volumeFull.png"));
        volume75 = new Texture(Gdx.files.internal("volume75.png"));
        volumeHalf = new Texture(Gdx.files.internal("volumeHalf.png"));
        volume25 = new Texture(Gdx.files.internal("volume25.png"));
        volumeOff = new Texture(Gdx.files.internal("volumeOff.png"));
        circled = new Texture(Gdx.files.internal("circled.png"));
        stickyNote = new Texture(Gdx.files.internal("stickyNote.png"));
        dim = new Texture(Gdx.files.internal("dim.png"));
        hintActive = new Texture(Gdx.files.internal("hintActive.png"));
        hintInactive = new Texture(Gdx.files.internal("hintInactive.png"));
        resetActive = new Texture(Gdx.files.internal("resetActive.png"));
        resetInactive = new Texture(Gdx.files.internal("resetInactive.png"));

        isPaused = false;
        newPuzzleClicked = false;
        mainMenuClicked = false;
        game.hintCounter = 0;
        game.gameTimer.reset();

    }

    @Override
    public void render(float delta) {

        ScreenUtils.clear(1, 1, 1, 1);

        game.music.checkNextSong();
        if (!isPaused) game.gameTimer.updateTime(delta);
        checkRowColumnSelection();
        if (sudokuPuzzle.isComplete() == SudokuPuzzle.CORRECT) {
            game.setScreen(new WinnerScreen(game));
        }

        GlyphLayout volume = new GlyphLayout(small, "Music Volume:");
        GlyphLayout effects = new GlyphLayout(small, "Sound Effects:");
        GlyphLayout mainMenu = new GlyphLayout(small, "Main Menu");
        GlyphLayout newPuzzle = new GlyphLayout(small, "New Puzzle");
        GlyphLayout easy = new GlyphLayout(small, "Easy");
        GlyphLayout medium = new GlyphLayout(small, "Medium");
        GlyphLayout hard = new GlyphLayout(small, "Hard");
        GlyphLayout expert = new GlyphLayout(small, "Expert");
        GlyphLayout youSure = new GlyphLayout(this.medium, "Are you sure?");
        GlyphLayout yes = new GlyphLayout(small, "Yes");
        GlyphLayout no = new GlyphLayout(small, "No");
        GlyphLayout timePlayed = new GlyphLayout(this.medium, game.gameTimer.toString());
        GlyphLayout one = new GlyphLayout(small, "1");
        GlyphLayout two = new GlyphLayout(small, "2");
        GlyphLayout three = new GlyphLayout(small, "3");
        GlyphLayout four = new GlyphLayout(small, "4");
        GlyphLayout five = new GlyphLayout(small, "5");
        GlyphLayout six = new GlyphLayout(small, "6");
        GlyphLayout seven = new GlyphLayout(small, "7");
        GlyphLayout eight = new GlyphLayout(small, "8");
        GlyphLayout nine = new GlyphLayout(small, "9");

        int threeX = Sudoku.WIDTH / 8 + 2 * (7 * Sudoku.WIDTH / 8) / 9;
        int fourX = Sudoku.WIDTH / 8 + 3 * (7 * Sudoku.WIDTH / 8) / 9;
        int fiveX = Sudoku.WIDTH / 8 + 4 * (7 * Sudoku.WIDTH / 8) / 9;
        int sixX = Sudoku.WIDTH / 8 + 5 * (7 * Sudoku.WIDTH / 8) / 9;
        int sevenX = Sudoku.WIDTH / 8 + 6 * (7 * Sudoku.WIDTH / 8) / 9;
        int eightX = Sudoku.WIDTH / 8 + 7 * (7 * Sudoku.WIDTH / 8) / 9;
        int nineX = Sudoku.WIDTH / 8 + 8 * (7 * Sudoku.WIDTH / 8) / 9;
        if (!isPaused) {

            if (isClicked(pauseX, pauseY + pauseDimensions, pauseDimensions, pauseDimensions)) {
                isPaused = true;
                if (game.soundOn) game.newMenu.play(0.5f);
            }

            if (isClicked(hintX, hintY + hintDimensions, hintDimensions, hintDimensions)) {
                if (game.soundOn) game.selectTick.play(0.8f);
                game.hintCounter++;
                sudokuPuzzle.getHint();
            }

            if (isClicked(resetX, resetY + resetHeight, resetWidth, resetHeight)) {
                if (game.soundOn) game.selectTick.play(0.8f);
                sudokuPuzzle.clearBoard();
            }

            // one
            if (isClicked(Sudoku.WIDTH / 8, Sudoku.HEIGHT / 4 + 16, (int) (one.width), (int) (one.height))) {
                if (rowSelected != 9 && colSelected != 9) {

                    if (sudokuPuzzle.getEditable()[rowSelected][colSelected]) {
                        if (game.soundOn)
                            game.selectTick.play(0.5f);

                        if (sudokuPuzzle.getBoard()[rowSelected][colSelected] == 1)
                            sudokuPuzzle.insertDigit(rowSelected, colSelected,0);
                        else
                            sudokuPuzzle.insertDigit(rowSelected, colSelected, 1);

                    }

                }
            }

            // two
            if (isClicked(Sudoku.WIDTH / 8 + (7 * Sudoku.WIDTH / 8) / 9, Sudoku.HEIGHT / 4 + 16, (int) (two.width), (int) (two.height))) {
                if (rowSelected != 9 && colSelected != 9) {

                    if (sudokuPuzzle.getEditable()[rowSelected][colSelected]) {
                        if (game.soundOn)
                            game.selectTick.play(0.5f);

                        if (sudokuPuzzle.getBoard()[rowSelected][colSelected] == 2)
                            sudokuPuzzle.insertDigit(rowSelected, colSelected,0);
                        else
                            sudokuPuzzle.insertDigit(rowSelected, colSelected, 2);

                    }

                }
            }

            // three
            if (isClicked(threeX, Sudoku.HEIGHT / 4 + 16, (int) (three.width), (int) (three.height))) {
                if (rowSelected != 9 && colSelected != 9) {

                    if (sudokuPuzzle.getEditable()[rowSelected][colSelected]) {
                        if (game.soundOn)
                            game.selectTick.play(0.5f);

                        if (sudokuPuzzle.getBoard()[rowSelected][colSelected] == 3)
                            sudokuPuzzle.insertDigit(rowSelected, colSelected,0);
                        else
                            sudokuPuzzle.insertDigit(rowSelected, colSelected, 3);

                    }

                }
            }

            // four
            if (isClicked(fourX, Sudoku.HEIGHT / 4 + 16, (int) (four.width), (int) (four.height))) {
                if (rowSelected != 9 && colSelected != 9) {

                    if (sudokuPuzzle.getEditable()[rowSelected][colSelected]) {
                        if (game.soundOn)
                            game.selectTick.play(0.5f);

                        if (sudokuPuzzle.getBoard()[rowSelected][colSelected] == 4)
                            sudokuPuzzle.insertDigit(rowSelected, colSelected,0);
                        else
                            sudokuPuzzle.insertDigit(rowSelected, colSelected, 4);

                    }

                }
            }

            // five
            if (isClicked(fiveX, Sudoku.HEIGHT / 4 + 16, (int) (five.width), (int) (five.height))) {
                if (rowSelected != 9 && colSelected != 9) {

                    if (sudokuPuzzle.getEditable()[rowSelected][colSelected]) {
                        if (game.soundOn)
                            game.selectTick.play(0.5f);

                        if (sudokuPuzzle.getBoard()[rowSelected][colSelected] == 5)
                            sudokuPuzzle.insertDigit(rowSelected, colSelected,0);
                        else
                            sudokuPuzzle.insertDigit(rowSelected, colSelected, 5);

                    }

                }
            }

            // six
            if (isClicked(sixX, Sudoku.HEIGHT / 4 + 16, (int) (six.width), (int) (six.height))) {
                if (rowSelected != 9 && colSelected != 9) {

                    if (sudokuPuzzle.getEditable()[rowSelected][colSelected]) {
                        if (game.soundOn)
                            game.selectTick.play(0.5f);

                        if (sudokuPuzzle.getBoard()[rowSelected][colSelected] == 6)
                            sudokuPuzzle.insertDigit(rowSelected, colSelected,0);
                        else
                            sudokuPuzzle.insertDigit(rowSelected, colSelected, 6);

                    }

                }
            }

            // seven
            if (isClicked(sevenX, Sudoku.HEIGHT / 4 + 16, (int) (seven.width), (int) (seven.height))) {
                if (rowSelected != 9 && colSelected != 9) {

                    if (sudokuPuzzle.getEditable()[rowSelected][colSelected]) {
                        if (game.soundOn)
                            game.selectTick.play(0.5f);

                        if (sudokuPuzzle.getBoard()[rowSelected][colSelected] == 7)
                            sudokuPuzzle.insertDigit(rowSelected, colSelected,0);
                        else
                            sudokuPuzzle.insertDigit(rowSelected, colSelected, 7);

                    }

                }
            }

            // eight
            if (isClicked(eightX, Sudoku.HEIGHT / 4 + 16, (int) (eight.width), (int) (eight.height))) {
                if (rowSelected != 9 && colSelected != 9) {

                    if (sudokuPuzzle.getEditable()[rowSelected][colSelected]) {
                        if (game.soundOn)
                            game.selectTick.play(0.5f);

                        if (sudokuPuzzle.getBoard()[rowSelected][colSelected] == 8)
                            sudokuPuzzle.insertDigit(rowSelected, colSelected,0);
                        else
                            sudokuPuzzle.insertDigit(rowSelected, colSelected, 8);

                    }

                }
            }

            // nine
            if (isClicked(nineX, Sudoku.HEIGHT / 4 + 16, (int) (nine.width), (int) (nine.height))) {
                if (rowSelected != 9 && colSelected != 9) {

                    if (sudokuPuzzle.getEditable()[rowSelected][colSelected]) {
                        if (game.soundOn)
                            game.selectTick.play(0.5f);

                        if (sudokuPuzzle.getBoard()[rowSelected][colSelected] == 9)
                            sudokuPuzzle.insertDigit(rowSelected, colSelected,0);
                        else
                            sudokuPuzzle.insertDigit(rowSelected, colSelected, 9);

                    }

                }
            }

        }
        else {

            if (!newPuzzleClicked && !mainMenuClicked) {

                if (isClicked(exitAMX, exitAMY + exitAMHeight, exitAMWidth, exitAMHeight)) {
                    isPaused = false;
                    if (game.soundOn) game.newMenu.play(0.5f);
                }

                if (isClicked(volumeX, volumeY + volumeDimensions, volumeDimensions, volumeDimensions)) {
                    game.musicVolume -= 0.25f;
                    if (game.soundOn)
                        game.selectTick.play(0.5f);
                    if (game.musicVolume < 0) game.musicVolume = 1;
                    game.music.setVolume(game.musicVolume);
                    Preferences pref = Gdx.app.getPreferences("volume");
                    pref.putFloat("music", game.musicVolume);
                    pref.flush();
                }

                if (isClicked(volumeX, effectsY + volumeDimensions, volumeDimensions, volumeDimensions)) {
                    game.soundOn = !game.soundOn;
                    Preferences pref = Gdx.app.getPreferences("volume");
                    pref.putBoolean("sounds", game.soundOn);
                    pref.flush();
                    if (game.soundOn)
                        game.selectTick.play(0.5f);
                }

                if (isClicked(mainMenuX, mainMenuY, (int) mainMenu.width, (int) mainMenu.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    mainMenuClicked = true;
                }

                if (isClicked((int) (newPuzzleX - newPuzzle.width), newPuzzleY, (int) newPuzzle.width, (int) newPuzzle.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    newPuzzleClicked = true;
                }
            }
            else if (mainMenuClicked) {

                if (isClicked((int) (Sudoku.WIDTH / 2f + selectDifficultyWidth / 2f - 240), selectDifficultyY + selectDifficultyHeight - 380, (int) no.width, (int) no.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    mainMenuClicked = false;
                }

                if (isClicked((int) (Sudoku.WIDTH / 2f - selectDifficultyWidth / 2f + 150), selectDifficultyY + selectDifficultyHeight - 380, (int) yes.width, (int) yes.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    game.setScreen(new MainMenuScreen(game));
                }

            }
            else {

                if (isClicked(selectDifficultyX + selectDifficultyWidth - 160, selectDifficultyY + exitAMHeight + selectDifficultyHeight - 190, exitAMWidth, exitAMHeight)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    newPuzzleClicked = false;
                }

                if (isClicked(Sudoku.WIDTH / 2 - 100, selectDifficultyY + selectDifficultyHeight - 230, (int) easy.width, (int) easy.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    game.setScreen(new MainGameScreen(game, 0));
                }

                if (isClicked(Sudoku.WIDTH / 2 - 100, selectDifficultyY + selectDifficultyHeight - 310, (int) medium.width, (int) medium.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    game.setScreen(new MainGameScreen(game, 1));
                }

                if (isClicked(Sudoku.WIDTH / 2 - 100, selectDifficultyY + selectDifficultyHeight - 390, (int) hard.width, (int) hard.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    game.setScreen(new MainGameScreen(game, 2));
                }

                if (isClicked(Sudoku.WIDTH / 2 - 100, selectDifficultyY + selectDifficultyHeight - 470, (int) expert.width, (int) expert.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    game.setScreen(new MainGameScreen(game, 3));
                }

            }
        }

        game.batch.begin();

        game.bg.render(game.batch);
        game.batch.draw(grid, gridX, gridY, gridDimensions, gridDimensions);
        boardDisplay.update();
        boardDisplay.render(game.batch, small, colXCoordinates, rowYCoordinates);
        this.medium.draw(game.batch, timePlayed, gameTimerX, gameTimerY);

        small.draw(game.batch, one, Sudoku.WIDTH / 8f, Sudoku.HEIGHT / 4f + 16);
        small.draw(game.batch, two, Sudoku.WIDTH / 8f + (7 * Sudoku.WIDTH / 8f) / 9, Sudoku.HEIGHT / 4f + 16);
        small.draw(game.batch, three, Sudoku.WIDTH / 8f + 2 * (7 * Sudoku.WIDTH / 8f) / 9, Sudoku.HEIGHT / 4f + 16);
        small.draw(game.batch, four, Sudoku.WIDTH / 8f + 3 * (7 * Sudoku.WIDTH / 8f) / 9, Sudoku.HEIGHT / 4f + 16);
        small.draw(game.batch, five, Sudoku.WIDTH / 8f + 4 * (7 * Sudoku.WIDTH / 8f) / 9, Sudoku.HEIGHT / 4f + 16);
        small.draw(game.batch, six, Sudoku.WIDTH / 8f + 5 * (7 * Sudoku.WIDTH / 8f) / 9, Sudoku.HEIGHT / 4f + 16);
        small.draw(game.batch, seven, Sudoku.WIDTH / 8f + 6 * (7 * Sudoku.WIDTH / 8f) / 9, Sudoku.HEIGHT / 4f + 16);
        small.draw(game.batch, eight, Sudoku.WIDTH / 8f + 7 * (7 * Sudoku.WIDTH / 8f) / 9, Sudoku.HEIGHT / 4f + 16);
        small.draw(game.batch, nine, Sudoku.WIDTH / 8f + 8 * (7 * Sudoku.WIDTH / 8f) / 9, Sudoku.HEIGHT / 4f + 16);

        if (rowSelected != 9 && colSelected != 9) {
            game.batch.draw(circled, colXCoordinates[colSelected] - 10, rowYCoordinates[rowSelected] - 45, boxCircledDimensions, boxCircledDimensions);
        }

        if (isHoveredOver(pauseX, pauseY + pauseDimensions, pauseDimensions, pauseDimensions) && !isPaused)
            game.batch.draw(pauseActive, pauseX, pauseY, pauseDimensions, pauseDimensions);
        else
            game.batch.draw(pauseInactive, pauseX, pauseY, pauseDimensions, pauseDimensions);

        if (isHoveredOver(resetX, resetY + resetHeight, resetWidth, resetHeight) && !isPaused)
            game.batch.draw(resetActive, resetX, resetY, resetWidth, resetHeight);
        else
            game.batch.draw(resetInactive, resetX, resetY, resetWidth, resetHeight);

        if (isHoveredOver(hintX, hintY + hintDimensions, hintDimensions, hintDimensions) && !isPaused)
            game.batch.draw(hintActive, hintX, hintY, hintDimensions, hintDimensions);
        else
            game.batch.draw(hintInactive, hintX, hintY, hintDimensions, hintDimensions);

        if (isPaused) {

            game.batch.draw(dim, -2 * Sudoku.WIDTH, -2 * Sudoku.HEIGHT, 6 * Sudoku.WIDTH, 6 * Sudoku.HEIGHT);

            if (!newPuzzleClicked && !mainMenuClicked) {

                game.batch.draw(pauseMenu, pauseMenuX - pauseMenu.getWidth() / 4f, pauseMenuY, pauseMenuWidth, pauseMenuHeight);
                if (isHoveredOver(exitAMX, exitAMY + exitAMHeight, exitAMWidth, exitAMHeight) && !newPuzzleClicked)
                    game.batch.draw(exitAMActive, exitAMX, exitAMY, exitAMWidth, exitAMHeight);
                else
                    game.batch.draw(exitAMInactive, exitAMX, exitAMY, exitAMWidth, exitAMHeight);

                small.draw(game.batch, volume, volumeX - volume.width - volumeOffsetX, volumeY + volume.height + volumeOffsetY);
                small.draw(game.batch, effects, volumeX - effects.width - effectsOffsetX, effectsY + effects.height + volumeOffsetY);
                small.draw(game.batch, mainMenu, mainMenuX, mainMenuY);
                small.draw(game.batch, newPuzzle, newPuzzleX - newPuzzle.width, newPuzzleY);

                if (game.musicVolume == 1)
                    game.batch.draw(volumeFull, volumeX, volumeY, volumeDimensions, volumeDimensions);
                else if (game.musicVolume == 0.75f)
                    game.batch.draw(volume75, volumeX, volumeY, volumeDimensions, volumeDimensions);
                else if (game.musicVolume == 0.5f)
                    game.batch.draw(volumeHalf, volumeX, volumeY, volumeDimensions, volumeDimensions);
                else if (game.musicVolume == 0.25f)
                    game.batch.draw(volume25, volumeX, volumeY, volumeDimensions, volumeDimensions);
                else
                    game.batch.draw(volumeOff, volumeX, volumeY, volumeDimensions, volumeDimensions);

                if (game.soundOn)
                    game.batch.draw(volumeFull, volumeX, effectsY, volumeDimensions, volumeDimensions);
                else
                    game.batch.draw(volumeOff, volumeX, effectsY, volumeDimensions, volumeDimensions);

                if (isHoveredOver(mainMenuX, mainMenuY, (int) mainMenu.width, (int) mainMenu.height)) {
                    game.batch.draw(circled, mainMenuX - pauseCircleOffsetX, mainMenuY - mainMenu.height - pauseCircleOffsetY, mainMenuCircleWidth, mainMenuCircleHeight);
                }

                if (isHoveredOver((int) (newPuzzleX - newPuzzle.width), newPuzzleY, (int) newPuzzle.width, (int) newPuzzle.height)) {
                    game.batch.draw(circled, newPuzzleX - newPuzzle.width - pauseCircleOffsetX, newPuzzleY - newPuzzle.height - pauseCircleOffsetY, newPuzzleCircleWidth, newPuzzleCircleHeight);
                }

            }
            else if (newPuzzleClicked){

                game.batch.draw(stickyNote, selectDifficultyX, selectDifficultyY, selectDifficultyWidth, selectDifficultyHeight);
                small.draw(game.batch, easy, Sudoku.WIDTH / 2f - 100, selectDifficultyY + selectDifficultyHeight - 230);
                small.draw(game.batch, medium, Sudoku.WIDTH / 2f - 100, selectDifficultyY + selectDifficultyHeight - 310);
                small.draw(game.batch, hard, Sudoku.WIDTH / 2f - 100, selectDifficultyY + selectDifficultyHeight - 390);
                small.draw(game.batch, expert, Sudoku.WIDTH / 2f - 100, selectDifficultyY + selectDifficultyHeight - 470);

                if (isHoveredOver(selectDifficultyX + selectDifficultyWidth - 160, selectDifficultyY + exitAMHeight + selectDifficultyHeight - 190, exitAMWidth, exitAMHeight))
                    game.batch.draw(exitAMActive, selectDifficultyX + selectDifficultyWidth - 160, selectDifficultyY + selectDifficultyHeight - 190, exitAMWidth, exitAMHeight);
                else
                    game.batch.draw(exitAMInactive, selectDifficultyX + selectDifficultyWidth - 160, selectDifficultyY + selectDifficultyHeight - 190, exitAMWidth, exitAMHeight);

                if (isHoveredOver(Sudoku.WIDTH / 2 - 100, selectDifficultyY + selectDifficultyHeight - 230, (int) easy.width, (int) easy.height))
                    game.batch.draw(circled,Sudoku.WIDTH / 2f - 110, selectDifficultyY + selectDifficultyHeight - easy.height - 260, easyCircleWidth, easyCircleHeight);
                if (isHoveredOver(Sudoku.WIDTH / 2 - 100, selectDifficultyY + selectDifficultyHeight - 310, (int) medium.width, (int) medium.height))
                    game.batch.draw(circled,Sudoku.WIDTH / 2f - 110, selectDifficultyY + selectDifficultyHeight - medium.height - 340, mediumCircleWidth, mediumCircleHeight);
                if (isHoveredOver(Sudoku.WIDTH / 2 - 100, selectDifficultyY + selectDifficultyHeight - 390, (int) hard.width, (int) hard.height))
                    game.batch.draw(circled,Sudoku.WIDTH / 2f - 110, selectDifficultyY + selectDifficultyHeight - hard.height - 420, hardCircleWidth, hardCircleHeight);
                if (isHoveredOver(Sudoku.WIDTH / 2 - 100, selectDifficultyY + selectDifficultyHeight - 470, (int) expert.width, (int) expert.height))
                    game.batch.draw(circled,Sudoku.WIDTH / 2f - 110, selectDifficultyY + selectDifficultyHeight - expert.height - 500, expertCircleWidth, expertCircleHeight);
            }
            else {

                game.batch.draw(stickyNote, selectDifficultyX, selectDifficultyY, selectDifficultyWidth, selectDifficultyHeight);

                this.medium.draw(game.batch, youSure, Sudoku.WIDTH / 2f - youSure.width / 2f, selectDifficultyY + selectDifficultyHeight - 180);
                small.draw(game.batch, yes, Sudoku.WIDTH / 2f - selectDifficultyWidth / 2f + 150, selectDifficultyY + selectDifficultyHeight - 380);
                small.draw(game.batch, no, Sudoku.WIDTH / 2f + selectDifficultyWidth / 2f - 240, selectDifficultyY + selectDifficultyHeight - 380);

                if (isHoveredOver((int) (Sudoku.WIDTH / 2f + selectDifficultyWidth / 2f - 240), selectDifficultyY + selectDifficultyHeight - 380, (int) no.width, (int) no.height))
                    game.batch.draw(circled, Sudoku.WIDTH / 2f + selectDifficultyWidth / 2f - 260, selectDifficultyY + selectDifficultyHeight - no.height - 400, noCircledWidth, yesCircledHeight);
                if (isHoveredOver((int) (Sudoku.WIDTH / 2f - selectDifficultyWidth / 2f + 150), selectDifficultyY + selectDifficultyHeight - 380, (int) yes.width, (int) yes.height))
                    game.batch.draw(circled, Sudoku.WIDTH / 2f - selectDifficultyWidth / 2f + 140, selectDifficultyY + selectDifficultyHeight - yes.height - 400, yesCircledWidth, yesCircledHeight);

            }
        }
        else {

            if (isHoveredOver(Sudoku.WIDTH / 8, Sudoku.HEIGHT / 4 + 16, (int) (one.width), (int) (one.height))) //one
                game.batch.draw(circled, Sudoku.WIDTH / 8f - 10, Sudoku.HEIGHT / 4f - one.height, boxCircledDimensions, boxCircledDimensions);
            else if (isHoveredOver(Sudoku.WIDTH / 8 + (7 * Sudoku.WIDTH / 8) / 9, Sudoku.HEIGHT / 4 + 16, (int) (two.width), (int) (two.height))) //two
                game.batch.draw(circled, Sudoku.WIDTH / 8f + (7 * Sudoku.WIDTH / 8f) / 9 - 10, Sudoku.HEIGHT / 4f - two.height, boxCircledDimensions, boxCircledDimensions);
            else if (isHoveredOver(threeX, Sudoku.HEIGHT / 4 + 16, (int) (three.width), (int) (three.height))) //three
                game.batch.draw(circled, Sudoku.WIDTH / 8f + 2 * (7 * Sudoku.WIDTH / 8f) / 9 - 10, Sudoku.HEIGHT / 4f - three.height, boxCircledDimensions, boxCircledDimensions);
            else if (isHoveredOver(fourX, Sudoku.HEIGHT / 4 + 16, (int) (four.width), (int) (four.height))) //four
                game.batch.draw(circled, Sudoku.WIDTH / 8f + 3 * (7 * Sudoku.WIDTH / 8f) / 9 - 10, Sudoku.HEIGHT / 4f - four.height, boxCircledDimensions, boxCircledDimensions);
            else if (isHoveredOver(fiveX, Sudoku.HEIGHT / 4 + 16, (int) (five.width), (int) (five.height))) //five
                game.batch.draw(circled, Sudoku.WIDTH / 8f + 4 * (7 * Sudoku.WIDTH / 8f) / 9 - 10, Sudoku.HEIGHT / 4f - five.height, boxCircledDimensions, boxCircledDimensions);
            else if (isHoveredOver(sixX, Sudoku.HEIGHT / 4 + 16, (int) (six.width), (int) (six.height))) //six
                game.batch.draw(circled, Sudoku.WIDTH / 8f + 5 * (7 * Sudoku.WIDTH / 8f) / 9 - 10, Sudoku.HEIGHT / 4f - six.height, boxCircledDimensions, boxCircledDimensions);
            else if (isHoveredOver(sevenX, Sudoku.HEIGHT / 4 + 16, (int) (seven.width), (int) (seven.height))) //seven
                game.batch.draw(circled, Sudoku.WIDTH / 8f + 6 * (7 * Sudoku.WIDTH / 8f) / 9 - 10, Sudoku.HEIGHT / 4f - seven.height, boxCircledDimensions, boxCircledDimensions);
            else if (isHoveredOver(eightX, Sudoku.HEIGHT / 4 + 16, (int) (eight.width), (int) (eight.height))) //eight
                game.batch.draw(circled, Sudoku.WIDTH / 8f + 7 * (7 * Sudoku.WIDTH / 8f) / 9 - 10, Sudoku.HEIGHT / 4f - eight.height, boxCircledDimensions, boxCircledDimensions);
            else if (isHoveredOver(nineX, Sudoku.HEIGHT / 4 + 16, (int) (nine.width), (int) (nine.height))) //nine
                game.batch.draw(circled, Sudoku.WIDTH / 8f + 8 * (7 * Sudoku.WIDTH / 8f) / 9 - 10, Sudoku.HEIGHT / 4f - nine.height, boxCircledDimensions, boxCircledDimensions);

        }

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {game.music.songs[game.music.numPlaying].pause();}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    private boolean isHoveredOver (int x, int y, int width, int height) {
        int mouseX = (int) game.camera.getInput().x, mouseY = (int) game.camera.getInput().y;
        return mouseX < (x + width) && mouseX > x && mouseY > y - height
                && mouseY < y;
    }

    private boolean isClicked (int x, int y, int width, int height) {
        return isHoveredOver(x, y, width, height) && Gdx.input.justTouched();
    }

    private void checkRowColumnSelection () {

        int colBefore = colSelected, rowBefore = rowSelected;

        if (isHoveredOver(colXCoordinates[0] - 20, rowYCoordinates[0] + 5, (colXCoordinates[8] + boxDimensions - 30) - (colXCoordinates[0] - 20), (rowYCoordinates[0] + 5) - (rowYCoordinates[8] - boxDimensions - 5))) {

            if (isClicked(colXCoordinates[0] - 20, gridY + gridDimensions, boxDimensions - 10, gridDimensions))
                colSelected = 0;
            else if (isClicked(colXCoordinates[1] - 20, gridY + gridDimensions, boxDimensions - 10, gridDimensions))
                colSelected = 1;
            else if (isClicked(colXCoordinates[2] - 20, gridY + gridDimensions, boxDimensions - 10, gridDimensions))
                colSelected = 2;
            else if (isClicked(colXCoordinates[3] - 20, gridY + gridDimensions, boxDimensions - 10, gridDimensions))
                colSelected = 3;
            else if (isClicked(colXCoordinates[4] - 20, gridY + gridDimensions, boxDimensions - 10, gridDimensions))
                colSelected = 4;
            else if (isClicked(colXCoordinates[5] - 20, gridY + gridDimensions, boxDimensions - 10, gridDimensions))
                colSelected = 5;
            else if (isClicked(colXCoordinates[6] - 20, gridY + gridDimensions, boxDimensions - 10, gridDimensions))
                colSelected = 6;
            else if (isClicked(colXCoordinates[7] - 20, gridY + gridDimensions, boxDimensions - 10, gridDimensions))
                colSelected = 7;
            else if (isClicked(colXCoordinates[8] - 20, gridY + gridDimensions, boxDimensions - 10, gridDimensions))
                colSelected = 8;

            if (isClicked(gridX, rowYCoordinates[0] + 5, gridDimensions, boxDimensions - 10))
                rowSelected = 0;
            else if (isClicked(gridX, rowYCoordinates[1] + 5, gridDimensions, boxDimensions - 10))
                rowSelected = 1;
            else if (isClicked(gridX, rowYCoordinates[2] + 5, gridDimensions, boxDimensions - 10))
                rowSelected = 2;
            else if (isClicked(gridX, rowYCoordinates[3] + 5, gridDimensions, boxDimensions - 10))
                rowSelected = 3;
            else if (isClicked(gridX, rowYCoordinates[4] + 5, gridDimensions, boxDimensions - 10))
                rowSelected = 4;
            else if (isClicked(gridX, rowYCoordinates[5] + 5, gridDimensions, boxDimensions - 10))
                rowSelected = 5;
            else if (isClicked(gridX, rowYCoordinates[6] + 5, gridDimensions, boxDimensions - 10))
                rowSelected = 6;
            else if (isClicked(gridX, rowYCoordinates[7] + 5, gridDimensions, boxDimensions - 10))
                rowSelected = 7;
            else if (isClicked(gridX, rowYCoordinates[8] + 5, gridDimensions, boxDimensions - 10))
                rowSelected = 8;
        }

        if (game.soundOn && rowSelected != 9 && colSelected != 9 && (rowSelected != rowBefore || colSelected != colBefore))
            game.selectTick.play(0.5f);
    }
}
