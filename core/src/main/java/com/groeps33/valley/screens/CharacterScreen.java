package com.groeps33.valley.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.groeps33.valley.TheValleyGame;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.entity.PlayerClass;

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
    ImageButton iButton3;

    Table rootTable;

    public CharacterScreen(final TheValleyGame game) {
        super(game);
        stage = new Stage();
        spriteBatch = new SpriteBatch();
        splashTexture = new Texture("menus/characters.jpg");
        rootTable = new Table();

        iButton1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("characters/mage.png"))));
        iButton2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("characters/warrior.png"))));
        iButton3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("characters/archer.png"))));
        iButton1.pad(12);
        iButton2.pad(12);
        iButton3.pad(12);

        Gdx.input.setInputProcessor(stage);
        iButton1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                game.setScreen(new GameScreen(game, PlayerClass.ARCHER));
            }
        });
        iButton2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                game.setScreen(new GameScreen(game, PlayerClass.WARRIOR));
            }
        });
        iButton3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                game.setScreen(new GameScreen(game, PlayerClass.ARCHER));
            }
        });

        rootTable.add(iButton1).size(170, 272);
        rootTable.add(iButton2).size(170, 272);
        rootTable.add(iButton3).size(170, 272);
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
