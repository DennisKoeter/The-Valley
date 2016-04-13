package com.groeps33.valley.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.groeps33.valley.entity.Character;

/**
 * Created by Roy on 13-4-2016.
 */
public class CharacterScreen extends TheValleyScreen {

    private final SpriteBatch spriteBatch;
    private final Texture splashTexture;

    private Character ch1;
    private Character ch2;
    private Character ch3;

    ImageButton iButton1;
    ImageButton iButton2;
    ImageButton iButton3;

    Table rootTable;

    CharacterScreen(Game game) {
        super(game);

        spriteBatch = new SpriteBatch();
        splashTexture = new Texture("menus/front(login).png");
//        rootTable = new Table();
//        ch1 = new Character(85, 3000, null, 0, 0, 0, 200);
//        TextureRegionDrawable textureRegionDrawable = new TextureRegionDrawable(ch1.getCharacterAnimator().getKeyFrame(10f,true));
//
//
//        iButton1 = new ImageButton()
//        iButton1.setBackground(textureRegionDrawable);
//        rootTable.add(iButton1);
//        rootTable.add(iButton2);
//        rootTable.add(iButton3);

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

    }
}
