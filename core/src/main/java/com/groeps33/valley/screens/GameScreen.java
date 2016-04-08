package com.groeps33.valley.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector3;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.renderer.OrthogonalTiledMapRendererWithSprites;

/**
 * Created by Bram on 6-4-2016.
 */
public class GameScreen extends TheValleyScreen {

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private OrthogonalTiledMapRendererWithSprites tiledMapRenderer;
    private Texture playerTexture;
    private Sprite playerSprite;
    private Character character;
//    private SpriteBatch spriteBash;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        character = new Character(0,0,null,0,0,0,5);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tiledMap = new TmxMapLoader().load("Map 1.tmx");
//        spriteBash = new SpriteBatch();
     //  playerTexture = new Texture(character.getCurrentFrame()));
       // playerSprite = new Sprite(playerTexture);
        tiledMapRenderer = new OrthogonalTiledMapRendererWithSprites(tiledMap);
       // tiledMapRenderer.addSprite(playerSprite);
        tiledMapRenderer.addCharacter(character);
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.LEFT)
                    camera.translate(-32, 0);
                if (keycode == Input.Keys.RIGHT)
                    camera.translate(32, 0);
                if (keycode == Input.Keys.UP)
                    camera.translate(0, -32);
                if (keycode == Input.Keys.DOWN)
                    camera.translate(0, 32);

                if (keycode == Input.Keys.NUM_1)
                    tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
                if (keycode == Input.Keys.NUM_2)
                    tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector3 clickCoordinates = new Vector3(screenX, screenY, 0);
                Vector3 position = camera.unproject(clickCoordinates);
              //  player.setPosition(position.x, position.y);
                playerSprite.setPosition(position.x - 32, position.y - 32);
                return true;
            }

        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        character.update(Gdx.graphics.getDeltaTime());
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

//        character.update(Gdx.graphics.getDeltaTime());
//
//        spriteBash.begin();
//        spriteBash.draw(character.getCurrentFrame(),character.getLocation().x,character.getLocation().y);
//        spriteBash.end();
//
//        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//            playerSprite.setPosition(playerSprite.getX(), playerSprite.getY() + 1);
//        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
//            playerSprite.setPosition(playerSprite.getX(), playerSprite.getY() - 1);
//        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
//            playerSprite.setPosition(playerSprite.getX() - 1, playerSprite.getY());
//        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
//            playerSprite.setPosition(playerSprite.getX() + 1, playerSprite.getY());
//        }
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
