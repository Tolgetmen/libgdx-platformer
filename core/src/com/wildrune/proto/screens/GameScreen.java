package com.wildrune.proto.screens;

import com.wildrune.proto.renderer.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.maps.*;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.math.*;

/**
 * @author Mark van der Wal
 * @since 10/02/18
 */
public class GameScreen extends Screen {

    private RuneTiledMapRenderer tiledMapRenderer;
    private TmxMapLoader mapLoader;
    private TiledMap currentMap;

    private float cameraSpeed = 128.0f;

    @Override
    public void show() {
        mapLoader = new TmxMapLoader();
        currentMap = mapLoader.load("maps/basic_map.tmx");

        tiledMapRenderer = new RuneTiledMapRenderer(currentMap);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.setDebugDraw(true);
    }

    @Override
    public void update(float delta) {
        moveCamera(delta);
        keepCameraWithinWorldBounds();
    }

    private void moveCamera(float deltaTime) {
        Vector2 dirVector = new Vector2();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dirVector.y = 1.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dirVector.y = -1.0f;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dirVector.x = -1.0f;
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dirVector.x = 1.0f;
        }

        if (!dirVector.epsilonEquals(0, 0, 0.00001f)) {
            dirVector.nor();
            dirVector.scl(cameraSpeed * deltaTime);

            camera.position.add(dirVector.x, dirVector.y, 0f);
        }
    }

    /***
     * Map coordinates are y up, x right, map starts at 0,0 going positive.
     * This way we know the map will never go into the negative quarter spaces.
     */
    private void keepCameraWithinWorldBounds() {
        MapProperties properties = currentMap.getProperties();

        // these are in world space coordinates
        int mapWidth = properties.get("width", Integer.class);
        int mapHeight = properties.get("height", Integer.class);
        int tilewidth = properties.get("tilewidth", Integer.class);
        int tileheight = properties.get("tileheight", Integer.class);

        Rectangle mapBounds = new Rectangle(0, 0, mapWidth * tilewidth, mapHeight * tileheight);
        Vector3 cameraPosition = camera.position;

        float halfWorldWidth = viewport.getWorldWidth() / 2.f;
        float halfWorldHeight = viewport.getWorldHeight() / 2.f;

        // need to get viewport bounds in world space
        if (cameraPosition.x - halfWorldWidth < mapBounds.x) {
            cameraPosition.set(mapBounds.x + halfWorldWidth, cameraPosition.y, cameraPosition.z);
        } else if (cameraPosition.x + halfWorldWidth > mapBounds.x + mapBounds.width) {
            cameraPosition.set(mapBounds.x + mapBounds.width - halfWorldWidth, cameraPosition.y, cameraPosition.z);
        }

        if (cameraPosition.y - halfWorldHeight < mapBounds.y) {
            cameraPosition.set(cameraPosition.x, mapBounds.y + halfWorldHeight, cameraPosition.z);
        } else if (cameraPosition.y + halfWorldHeight > mapBounds.y + mapBounds.height) {
            cameraPosition.set(cameraPosition.x, mapBounds.y + mapBounds.height - halfWorldHeight, cameraPosition.z);
        }

        viewport.apply();
    }

    @Override
    public void draw() {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    @Override
    public void dispose() {
        tiledMapRenderer.dispose();
    }
}
