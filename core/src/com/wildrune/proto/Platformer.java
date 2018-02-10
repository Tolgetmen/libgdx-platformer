package com.wildrune.proto;

import com.wildrune.proto.screens.*;

import com.badlogic.gdx.*;

public class Platformer extends Game {

    @Override
    public void create() {
        setScreen(new MapScreen());
    }
}
