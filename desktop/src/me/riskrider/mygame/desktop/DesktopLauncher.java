package me.riskrider.mygame.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.riskrider.mygame.Sudoku;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.resizable = false;
		config.width = Sudoku.WIDTH;
		config.height = Sudoku.HEIGHT;
		config.addIcon("icon_16.png", Files.FileType.Internal);
		config.addIcon("icon_32.png", Files.FileType.Internal);
		config.addIcon("icon_64.png", Files.FileType.Internal);
		config.addIcon("icon_128.png", Files.FileType.Internal);
		new LwjglApplication(new Sudoku(), config);
	}
}
