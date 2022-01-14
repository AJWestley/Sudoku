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

public class MainMenuScreen implements Screen {

    private final int easyY = 780;
    private final int mediumY = 660;
    private final int hardY = 540;
    private final int expertY = 420;
    private final int audioY = 250;
    private final int exitY = 170;
    private final int audioCircledWidth = 150;
    private final int audioCircledHeight = 60;
    private final int exitCircledWidth = 120;
    private final int exitCircledHeight = 60;
    private final int difficultiesCircledHeight = 80;
    private final int easyCircledWidth = 170;
    private final int mediumCircledWidth = 250;
    private final int hardCircledWidth = 180;
    private final int expertCircledWidth = 220;
    private final float yOffset = 20;
    private final float xOffset = 20;
    private final int audioMenuX = Sudoku.WIDTH / 2;
    private final int audioMenuY = Sudoku.HEIGHT / 2;
    private final int audioMenuWidth = 960;
    private final int audioMenuHeight = 540;
    private final int volumeX = (3 * Sudoku.WIDTH / 4) - 30;
    private final int volumeY = audioMenuY + 20;
    private final int effectsY = volumeY - 150;
    private final int volumeDimensions = 100;
    private final int volumeOffsetX = 60;
    private final int volumeOffsetY = 30;
    private final int effectsOffsetX = 30;
    private final int exitAMX = 7 * Sudoku.WIDTH / 8;
    private final int exitAMY = volumeY - 155;
    private final int exitAMWidth = 75;
    private final int exitAMHeight = 75;

    boolean audioMenuShowing;
    boolean exitMenuShowing;

    BitmapFont title;
    BitmapFont difficulties;
    BitmapFont exit;
    Texture volumeFull;
    Texture volume75;
    Texture volumeHalf;
    Texture volume25;
    Texture volumeOff;
    Texture circled;
    Texture audioMenu;
    Texture exitAMInactive;
    Texture exitAMActive;
    Texture dim;

    Sudoku game;

    public MainMenuScreen (Sudoku game) {
        this.game = game;
        title = new BitmapFont(Gdx.files.internal("title/font.fnt"));
        title.setColor(Color.BLACK);
        difficulties = new BitmapFont(Gdx.files.internal("difficulties/font.fnt"));
        difficulties.setColor(Color.BLACK);
        exit = new BitmapFont(Gdx.files.internal("exit/font.fnt"));
        exit.setColor(Color.BLACK);

        audioMenuShowing = false;
        exitMenuShowing = false;

        volumeFull = new Texture(Gdx.files.internal("volumeFull.png"));
        volume75 = new Texture(Gdx.files.internal("volume75.png"));
        volumeHalf = new Texture(Gdx.files.internal("volumeHalf.png"));
        volume25 = new Texture(Gdx.files.internal("volume25.png"));
        volumeOff = new Texture(Gdx.files.internal("volumeOff.png"));
        circled = new Texture(Gdx.files.internal("circled.png"));
        audioMenu = new Texture(Gdx.files.internal("audioMenu.png"));
        exitAMInactive = new Texture(Gdx.files.internal("exitAudoMenuInactive.png"));
        exitAMActive = new Texture(Gdx.files.internal("exitAudoMenuActive.png"));
        dim = new Texture(Gdx.files.internal("dim.png"));

    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {

        ScreenUtils.clear(1, 1, 1, 1);

        GlyphLayout title = new GlyphLayout(this.title, "Sudoku");
        GlyphLayout easy = new GlyphLayout(difficulties, "Easy");
        GlyphLayout medium = new GlyphLayout(difficulties, "Medium");
        GlyphLayout hard = new GlyphLayout(difficulties, "Hard");
        GlyphLayout expert = new GlyphLayout(difficulties, "Expert");
        GlyphLayout exit = new GlyphLayout(this.exit, "Exit");
        GlyphLayout audio = new GlyphLayout(this.exit, "Audio");
        GlyphLayout yes = new GlyphLayout(this.exit, "Yes");
        GlyphLayout no = new GlyphLayout(this.exit, "No");

        game.music.checkNextSong();

        // Check mouse input
        if (!audioMenuShowing) {
            if (!exitMenuShowing) {

                if (isClicked(Sudoku.WIDTH / 8, exitY, (int) exit.width, (int) exit.height)) {
                    exitMenuShowing = true;
                    if (game.soundOn) game.newMenu.play(0.5f);
                }

                if (isClicked(Sudoku.WIDTH / 8, audioY, (int) audio.width, (int) audio.height)) {
                    audioMenuShowing = true;
                    if (game.soundOn) game.newMenu.play(0.5f);
                }

                if (isClicked(Sudoku.WIDTH / 8, easyY, (int) easy.width, (int) easy.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    game.setScreen(new MainGameScreen(game, 0));
                }

                if (isClicked(Sudoku.WIDTH / 8, mediumY, (int) medium.width, (int) medium.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    game.setScreen(new MainGameScreen(game, 1));
                }

                if (isClicked(Sudoku.WIDTH / 8, hardY, (int) hard.width, (int) hard.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    game.setScreen(new MainGameScreen(game, 2));
                }

                if (isClicked(Sudoku.WIDTH / 8, expertY, (int) expert.width, (int) expert.height)) {
                    if (game.soundOn) game.newMenu.play(0.8f);
                    game.setScreen(new MainGameScreen(game, 3));
                }

            } else {

                if (isClicked((int) (Sudoku.WIDTH / 4 - yes.width / 2), audioMenuY - 50, (int) yes.width, (int) yes.height)) {
                    Gdx.app.exit();
                }

                if (isClicked((int) (3 * Sudoku.WIDTH / 4 - no.width / 2), audioMenuY - 50, (int) no.width, (int) no.height)) {
                    exitMenuShowing = false;
                    if (game.soundOn) game.newMenu.play(0.5f);
                }

            }
        } else {

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

            if (isClicked(exitAMX, exitAMY, exitAMWidth, exitAMHeight)) {
                audioMenuShowing = false;
                if (game.soundOn)
                    game.newMenu.play(0.5f);
            }
        }

        game.batch.begin();

        game.bg.render(game.batch);

        this.title.draw(game.batch, title, Sudoku.WIDTH / 2f - title.width / 2, Sudoku.HEIGHT - title.height - 10);
        difficulties.draw(game.batch, easy, Sudoku.WIDTH / 8f, easyY);
        difficulties.draw(game.batch, medium, Sudoku.WIDTH / 8f, mediumY);
        difficulties.draw(game.batch, hard, Sudoku.WIDTH / 8f, hardY);
        difficulties.draw(game.batch, expert, Sudoku.WIDTH / 8f, expertY);
        this.exit.draw(game.batch, audio, Sudoku.WIDTH / 8f, audioY);
        this.exit.draw(game.batch, exit, Sudoku.WIDTH / 8f, exitY);

        if (!exitMenuShowing && !audioMenuShowing) {
            if (isHoveredOver(Sudoku.WIDTH / 8, easyY, (int) easy.width, (int) easy.height)) {
                game.batch.draw(circled, Sudoku.WIDTH / 8f - xOffset, easyY - easy.height - yOffset, easyCircledWidth, difficultiesCircledHeight);
            }

            if (isHoveredOver(Sudoku.WIDTH / 8, mediumY, (int) medium.width, (int) medium.height)) {
                game.batch.draw(circled, Sudoku.WIDTH / 8f - xOffset, mediumY - medium.height - yOffset, mediumCircledWidth, difficultiesCircledHeight);
            }

            if (isHoveredOver(Sudoku.WIDTH / 8, hardY, (int) hard.width, (int) hard.height)) {
                game.batch.draw(circled, Sudoku.WIDTH / 8f - xOffset, hardY - hard.height - yOffset, hardCircledWidth, difficultiesCircledHeight);
            }

            if (isHoveredOver(Sudoku.WIDTH / 8, expertY, (int) expert.width, (int) expert.height)) {
                game.batch.draw(circled, Sudoku.WIDTH / 8f - xOffset, expertY - expert.height - yOffset, expertCircledWidth, difficultiesCircledHeight);
            }

            if (isHoveredOver(Sudoku.WIDTH / 8, audioY, (int) audio.width, (int) audio.height)) {
                game.batch.draw(circled, Sudoku.WIDTH / 8f - xOffset, audioY - audio.height - yOffset, audioCircledWidth, audioCircledHeight);
            }

            if (isHoveredOver(Sudoku.WIDTH / 8, exitY, (int) exit.width, (int) exit.height)) {
                game.batch.draw(circled, Sudoku.WIDTH / 8f - xOffset, exitY - exit.height - yOffset, exitCircledWidth, exitCircledHeight);
            }
        }

        if (audioMenuShowing) {

            game.batch.draw(dim, -2 * Sudoku.WIDTH, -2 * Sudoku.HEIGHT, 6 * Sudoku.WIDTH, 6 * Sudoku.HEIGHT);
            game.batch.draw(audioMenu, audioMenuX - audioMenu.getWidth() / 4f, audioMenuY - audioMenuHeight / 2, audioMenuWidth, audioMenuHeight);

            GlyphLayout volume = new GlyphLayout(difficulties, "Music Volume:");
            GlyphLayout effects = new GlyphLayout(difficulties, "Sound Effects:");

            difficulties.draw(game.batch, volume, volumeX - volume. width - volumeOffsetX, volumeY + volume.height + volumeOffsetY);
            difficulties.draw(game.batch, effects, volumeX - effects.width - effectsOffsetX, effectsY + effects.height + volumeOffsetY);

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

            if (isHoveredOver(exitAMX, exitAMY, exitAMWidth, exitAMHeight))
                game.batch.draw(exitAMActive, exitAMX, exitAMY - exitAMHeight, exitAMWidth, exitAMHeight);
            else
                game.batch.draw(exitAMInactive, exitAMX, exitAMY - exitAMHeight, exitAMWidth, exitAMHeight);

        }

        if (exitMenuShowing) {

            game.batch.draw(dim, -2 * Sudoku.WIDTH, -2 * Sudoku.HEIGHT, 6 * Sudoku.WIDTH, 6 * Sudoku.HEIGHT);
            game.batch.draw(audioMenu, audioMenuX - audioMenu.getWidth() / 4f, audioMenuY - audioMenuHeight / 2, audioMenuWidth, audioMenuHeight);

            GlyphLayout sure = new GlyphLayout(difficulties, "Exit game?");

            difficulties.draw(game.batch, sure, Sudoku.WIDTH / 2f - sure.width / 2, audioMenuY + 100);
            this.exit.draw(game.batch, yes, Sudoku.WIDTH / 4f - yes.width / 2, audioMenuY - 50);
            this.exit.draw(game.batch, no, 3 * Sudoku.WIDTH / 4f - no.width / 2, audioMenuY - 50);

            if (isHoveredOver((int) (Sudoku.WIDTH / 4 - yes.width / 2), audioMenuY - 50, (int) yes.width, (int) yes.height))
                game.batch.draw(circled, Sudoku.WIDTH / 4f - yes.width + 25, audioMenuY - 65 - yes.height, exitCircledWidth - 30, exitCircledHeight);
            if (isHoveredOver((int) (3 * Sudoku.WIDTH / 4 - no.width / 2), audioMenuY - 50, (int) no.width, (int) no.height))
                game.batch.draw(circled, 3 * Sudoku.WIDTH / 4f - no.width / 2 - 20, audioMenuY - 65 - no.height, exitCircledWidth - 40, exitCircledHeight);

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
