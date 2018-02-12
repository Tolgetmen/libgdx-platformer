package com.wildrune.proto.screens;

import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.*;

/**
 * @author Mark van der Wal
 * @since 10/02/18
 */
public class GameScreen extends Screen {

    private TiledMap currentMap;
    private TiledMapRenderer tiledMapRenderer;

    @Override
    public void show() {
        currentMap = new TmxMapLoader().load("maps/basic_map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(currentMap);
        tiledMapRenderer.setView(camera);
    }

    @Override
    public void update(float delta) {
    }

    @Override
    public void draw() {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }
}
