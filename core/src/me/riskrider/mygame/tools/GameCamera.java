package me.riskrider.mygame.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
public class GameCamera {

    private OrthographicCamera camera;
    private ExtendViewport viewport;

    public GameCamera (int width, int height) {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(width, height, camera);
        viewport.apply();
        camera.position.set(width / 2f, height / 2f, 0);
        camera.update();
    }

    public Matrix4 combined () {
        return camera.combined;
    }

    public void update (int width, int height) {
        viewport.update(width, height);
    }

    public Vector2 getInput () {
        Vector3 inputScreen = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        Vector3 unprojected = camera.unproject(inputScreen);
        return new Vector2(unprojected.x, unprojected.y);
    }

}
