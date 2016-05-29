package com.groeps33.valley.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.groeps33.valley.entity.Character;

/**
 * Created by Roy on 13-4-2016.
 */
public class CharacterScreen extends TheValleyScreen {

    private final SpriteBatch spriteBatch;
    private final Texture splashTexture;
    Stage stage;

    private Character ch1;
    private Character ch2;
    private Character ch3;

    ImageButton iButton1;
    ImageButton iButton2;

    Table rootTable;

    CharacterScreen(final Game game) {
        super(game);
        stage = new Stage();
        spriteBatch = new SpriteBatch();
        splashTexture = new Texture("menus/front(login).png");
        rootTable = new Table();

        iButton1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("temp/warrior.png"))));
        iButton2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("temp/mage.png"))));

        Gdx.input.setInputProcessor(stage);
        iButton1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        iButton2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });

        rootTable.add(iButton1);
        rootTable.pad(20);
        rootTable.add(iButton2);
        stage.addActor(rootTable);
        rootTable.setFillParent(true);
        rootTable.center();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(splashTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();
        stage.draw();

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
