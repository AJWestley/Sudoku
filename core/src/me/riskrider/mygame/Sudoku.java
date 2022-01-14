package me.riskrider.mygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.riskrider.mygame.GameScreens.MainMenuScreen;
import me.riskrider.mygame.entities.BackGround;
import me.riskrider.mygame.tools.GameCamera;
import me.riskrider.mygame.tools.Playlist;
import me.riskrider.mygame.tools.Timer;

public class Sudoku extends Game {

	public static final int HEIGHT = 1280;
	public static final int WIDTH = 720;

	public SpriteBatch batch;
	public GameCamera camera;
	public BackGround bg;
	public Timer gameTimer;
	public float musicVolume;
	public boolean soundOn;
	public boolean nc;
	public int hintCounter;

	public Playlist music;
	public Sound selectTick;
	public Sound newMenu;
	public Sound nice;
	public Sound victory;

	@Override
	public void create() {

		Preferences prefs = Gdx.app.getPreferences("volume");
		musicVolume = prefs.getFloat("music", 1);
		soundOn = prefs.getBoolean("sounds", true);
		prefs.flush();

		music = new Playlist();
		music.setVolume(musicVolume);
		music.play();

		nc = true;

		gameTimer = new Timer();

		selectTick = Gdx.audio.newSound(Gdx.files.internal("sounds/select.mp3"));
		newMenu = Gdx.audio.newSound(Gdx.files.internal("sounds/newScreen.mp3"));
		nice = Gdx.audio.newSound(Gdx.files.internal("sounds/nice.mp3"));
		victory = Gdx.audio.newSound(Gdx.files.internal("sounds/cheer.mp3"));

		batch = new SpriteBatch();

		camera = new GameCamera(WIDTH, HEIGHT);

		bg = new BackGround();

		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(camera.combined());
		super.render();
	}

	@Override
	public void resize (int width, int height) {
		camera.update(width, height);
		super.resize(width, height);
	}
}
