package com.groeps33.valley.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.groeps33.valley.TheValleyGame;

/**
 * Created by Bram on 6-4-2016.
 */
abstract class TheValleyScreen implements Screen {
    protected final TheValleyGame game;

    TheValleyScreen(TheValleyGame game) {
        this.game = game;
    }
}
