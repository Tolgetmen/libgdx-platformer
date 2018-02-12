package com.wildrune.proto;

import com.wildrune.proto.screens.*;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;

public class Platformer extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(66f / 255f, 134f / 255f, 244 / 255f, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        super.render();
    }
}
