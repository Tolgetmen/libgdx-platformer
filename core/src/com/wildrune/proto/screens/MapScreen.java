package com.wildrune.proto.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;
import com.badlogic.gdx.utils.viewport.*;

/**
 * @author Mark van der Wal
 * @since 10/02/18
 */
public class MapScreen implements Screen {

    OrthographicCamera camera;
    FitViewport fitViewport;
    TiledMap currentMap;
    TiledMapRenderer tiledMapRenderer;

    @Override
    public void show() {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 640, 360);
        camera.update();
        fitViewport = new FitViewport(640, 360, camera);

        currentMap = new TmxMapLoader().load("maps/basic_map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(currentMap);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(66f / 255f, 134f / 255f, 244/255f, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        fitViewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
