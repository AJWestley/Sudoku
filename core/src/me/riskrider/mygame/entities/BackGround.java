package me.riskrider.mygame.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import me.riskrider.mygame.Sudoku;

public class BackGround {

    public Texture page, desk;
    public float pageWidth, pageHeight, deskWidth, deskHeight;

    public BackGround () {

        page = new Texture("pg_page.jpeg");
        pageHeight = Sudoku.HEIGHT;
        float pageScale = pageHeight / page.getHeight();
        pageWidth = page.getWidth() * pageScale;

        desk = new Texture("wood_bg.jpg");
        deskHeight = 1.5f * Sudoku.HEIGHT;
        float deskScale = deskHeight / desk.getHeight();
        deskWidth = desk.getWidth() * deskScale;

    }

    public void render (SpriteBatch batch) {
        batch.draw(desk, Sudoku.WIDTH / 2f - deskWidth / 2, Sudoku.HEIGHT / 2f - deskHeight / 2, deskWidth, deskHeight);
        batch.draw(page, 0, 0, pageWidth, pageHeight);
    }
}
