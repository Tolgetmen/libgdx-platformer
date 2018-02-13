package com.wildrune.proto.screens;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.utils.viewport.*;

/**
 * @author Mark van der Wal
 * @since 11/02/18
 */
public abstract class Screen implements com.badlogic.gdx.Screen {

    private int ideal_width = 480;
    private int ideal_height = 270;

    protected OrthographicCamera camera;
    protected Viewport viewport;

    abstract public void update(float delta);
    abstract public void draw();

    public Screen() {
        camera = new OrthographicCamera();
        viewport = new FitViewport(ideal_width, ideal_height, camera);
    }

    @Override
    public void render(float delta) {
        update(delta);
        draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    // Unused
    public void pause() {}
    public void resume() {}
    public void show() {}
    public void hide() {}
    public void dispose() {}
}

