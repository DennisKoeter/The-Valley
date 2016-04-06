package com.groeps33.valley.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Created by Bram on 6-4-2016.
 */
public abstract class TheValleyScreen implements Screen {
    private final Game game;

    protected TheValleyScreen(Game game) {
        this.game = game;
    }
}
