package me.riskrider.mygame.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import me.riskrider.mygame.Sudoku;

public class WinnerScreen implements Screen {

    Sudoku game;

    boolean selectDifficulty;

    Texture rip;
    Texture circle;
    Texture sticky;
    Texture dim;
    Texture backActive;
    Texture backInactive;
    BitmapFont large;
    BitmapFont medium;
    BitmapFont small;

    public WinnerScreen (Sudoku game) {

        this.game = game;

    }

    @Override
    public void show() {

        large = new BitmapFont(Gdx.files.internal("title/font.fnt"));
        large.setColor(Color.BLACK);
        medium = new BitmapFont(Gdx.files.internal("difficulties/font.fnt"));
        medium.setColor(Color.BLACK);
        small = new BitmapFont(Gdx.files.internal("exit/font.fnt"));
        small.setColor(Color.BLACK);

        rip = new Texture(Gdx.files.internal("audioMenu.png"));
        sticky = new Texture(Gdx.files.internal("stickyNote.png"));
        circle = new Texture(Gdx.files.internal("circled.png"));
        dim = new Texture(Gdx.files.internal("dim.png"));
        backActive = new Texture(Gdx.files.internal("exitAudoMenuActive.png"));
        backInactive = new Texture(Gdx.files.internal("exitAudoMenuInactive.png"));
        selectDifficulty = false;

        if (game.soundOn) {
            if (game.nc) game.nice.play();
            else game.victory.play(0.25f);
        }

    }

    @Override
    public void render(float delta) {

        int exitX = Sudoku.WIDTH / 2 + 130, exitY = Sudoku.HEIGHT / 2 - 230, exitDimensions = 75;

        GlyphLayout wellDone = new GlyphLayout(large, "Well Done!");
        GlyphLayout time = new GlyphLayout(small, "You completed the puzzle in " + game.gameTimer.toString() + " and you used " + game.hintCounter + " hints!", Color.BLACK, 4 * Sudoku.WIDTH / 5, 1, true);
        GlyphLayout again = new GlyphLayout(small, "Play again");
        GlyphLayout menu = new GlyphLayout(small, "Main menu");
        GlyphLayout easy = new GlyphLayout(small, "Easy");
        GlyphLayout medium = new GlyphLayout(small, "Medium");
        GlyphLayout hard = new GlyphLayout(small, "Hard");
        GlyphLayout expert = new GlyphLayout(small, "Expert");

        if (!selectDifficulty) {
            if (isClicked((int) (5 * Sudoku.WIDTH / 6f - again.width / 2), (int) (Sudoku.HEIGHT / 2f + 170), (int) (again.width), (int) (again.height))) {
                if (game.soundOn) game.newMenu.play(0.5f);
                game.setScreen(new MainMenuScreen(game));
            }

            if (isClicked((int) (Sudoku.WIDTH / 6f - again.width / 2), (int) (Sudoku.HEIGHT / 2f + 170), (int) (again.width), (int) (again.height))) {
                if (game.soundOn) game.newMenu.play(0.5f);
                selectDifficulty = true;
            }
        }
        else {
            if (isClicked(exitX, exitY + exitDimensions, exitDimensions, exitDimensions)) {
                if (game.soundOn) game.newMenu.play(0.5f);
                selectDifficulty = false;
            }
            else if (isClicked((int) (Sudoku.WIDTH / 2 - easy.width / 2), Sudoku.HEIGHT / 2 + 100, (int) (easy.width), (int) (easy.height))) {
                if (game.soundOn) game.newMenu.play(0.5f);
                game.setScreen(new MainGameScreen(game, 0));
            }
             else if (isClicked((int) (Sudoku.WIDTH / 2 - medium.width / 2), Sudoku.HEIGHT / 2 + 20, (int) (medium.width), (int) (medium.height))) {
                if (game.soundOn) game.newMenu.play(0.5f);
                game.setScreen(new MainGameScreen(game, 1));
            }
            else if (isClicked((int) (Sudoku.WIDTH / 2 - hard.width / 2), Sudoku.HEIGHT / 2 - 60, (int) (hard.width), (int) (hard.height))) {
                if (game.soundOn) game.newMenu.play(0.5f);
                game.setScreen(new MainGameScreen(game, 2));
            }
            else if (isClicked((int) (Sudoku.WIDTH / 2 - expert.width / 2), Sudoku.HEIGHT / 2 - 140, (int) (expert.width), (int) (expert.height))) {
                if (game.soundOn) game.newMenu.play(0.5f);
                game.setScreen(new MainGameScreen(game, 3));
            }
        }

        game.batch.begin();

        game.bg.render(game.batch);
        game.batch.draw(dim, -2 * Sudoku.WIDTH, -2 * Sudoku.HEIGHT, 6 * Sudoku.WIDTH, 6 * Sudoku.HEIGHT);
        game.batch.draw(rip, Sudoku.WIDTH / 2 - 480, Sudoku.HEIGHT / 2, 960, 540);

        large.draw(game.batch, wellDone, Sudoku.WIDTH / 2f - wellDone.width / 2, Sudoku.HEIGHT / 2f + 440);
        small.draw(game.batch, time, Sudoku.WIDTH / 2f - time.width / 2, Sudoku.HEIGHT / 2f + 340);
        small.draw(game.batch, again, Sudoku.WIDTH / 6f - again.width / 2, Sudoku.HEIGHT / 2f + 170);
        small.draw(game.batch, menu, 5 * Sudoku.WIDTH / 6f - again.width / 2, Sudoku.HEIGHT / 2f + 170);

        if (!selectDifficulty) {
            if (isHoveredOver((int) (Sudoku.WIDTH / 6f - again.width / 2), (int) (Sudoku.HEIGHT / 2f + 170), (int) (again.width), (int) (again.height))) {
                game.batch.draw(circle, (int) (Sudoku.WIDTH / 6f - again.width / 2 - 10), (int) (Sudoku.HEIGHT / 2f - again.height + 140), 240, 80);
            }
            if (isHoveredOver((int) (5 * Sudoku.WIDTH / 6f - again.width / 2), (int) (Sudoku.HEIGHT / 2f + 170), (int) (again.width), (int) (again.height))) {
                game.batch.draw(circle, (int) (5 * Sudoku.WIDTH / 6f - menu.width / 2 - 20), (int) (Sudoku.HEIGHT / 2f - menu.height + 140), 250, 80);
            }
        }
        else {
            game.batch.draw(dim, -2 * Sudoku.WIDTH, -2 * Sudoku.HEIGHT, 6 * Sudoku.WIDTH, 6 * Sudoku.HEIGHT);
            game.batch.draw(sticky, Sudoku.WIDTH / 2f - sticky.getWidth() / 2f, Sudoku.HEIGHT / 2f - sticky.getHeight() / 2f);
            small.draw(game.batch, easy, Sudoku.WIDTH / 2 - easy.width / 2, Sudoku.HEIGHT / 2 + 100);
            small.draw(game.batch, medium, Sudoku.WIDTH / 2 - medium.width / 2, Sudoku.HEIGHT / 2 + 20);
            small.draw(game.batch, hard, Sudoku.WIDTH / 2 - hard.width / 2, Sudoku.HEIGHT / 2 - 60);
            small.draw(game.batch, expert, Sudoku.WIDTH / 2 - expert.width / 2, Sudoku.HEIGHT / 2 - 140);

            if (isHoveredOver(exitX, exitY + exitDimensions, exitDimensions, exitDimensions))
                game.batch.draw(backActive, exitX, exitY, exitDimensions, exitDimensions);
            else
                game.batch.draw(backInactive, exitX, exitY, exitDimensions, exitDimensions);

            if (isHoveredOver((int) (Sudoku.WIDTH / 2 - easy.width / 2), Sudoku.HEIGHT / 2 + 100, (int) (easy.width), (int) (easy.height)))
                game.batch.draw(circle, Sudoku.WIDTH / 2f - easy.width / 2 - 5, Sudoku.HEIGHT / 2f + easy.height + 15, 110, 60);
            if (isHoveredOver((int) (Sudoku.WIDTH / 2 - medium.width / 2), Sudoku.HEIGHT / 2 + 20, (int) (medium.width), (int) (medium.height)))
                game.batch.draw(circle, Sudoku.WIDTH / 2f - medium.width / 2 - 15, Sudoku.HEIGHT / 2f + medium.height - 65, 180, 60);
            if (isHoveredOver((int) (Sudoku.WIDTH / 2 - hard.width / 2), Sudoku.HEIGHT / 2 - 60, (int) (hard.width), (int) (hard.height)))
                game.batch.draw(circle, Sudoku.WIDTH / 2f - hard.width / 2 - 15, Sudoku.HEIGHT / 2f + hard.height - 140, 130, 60);
            if (isHoveredOver((int) (Sudoku.WIDTH / 2 - expert.width / 2), Sudoku.HEIGHT / 2 - 140, (int) (expert.width), (int) (expert.height)))
                game.batch.draw(circle, Sudoku.WIDTH / 2f - expert.width / 2 - 15, Sudoku.HEIGHT / 2f + expert.height - 220, 160, 60);
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
}
