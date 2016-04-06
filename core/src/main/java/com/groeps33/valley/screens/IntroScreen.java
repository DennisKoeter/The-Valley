package com.groeps33.valley.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Bram on 6-4-2016.
 */
public class IntroScreen extends TheValleyScreen {

    private final SpriteBatch spriteBatch;
    private final Texture splashTexture;

    public IntroScreen(Game game) {
        super(game);
        spriteBatch = new SpriteBatch();
        splashTexture = new Texture("menus/front(login).png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(splashTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        splashTexture.dispose();
        spriteBatch.dispose();
    }
}
