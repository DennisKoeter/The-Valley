package com.groeps33.valley.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.entity.Entity;
import com.groeps33.valley.renderer.TiledMapRendererWithEntities;

/**
 * Created by Bram on 6-4-2016.
 */
public class GameScreen extends TheValleyScreen {

    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRendererWithEntities tiledMapRenderer;
    private Texture playerTexture;
    private Sprite playerSprite;
    private Character character;
    private MapObjects objects;
//    private SpriteBatch spriteBash;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        character = new Character(85,3000,null,0,0,0,5);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tiledMap = new TmxMapLoader().load("Map 2.tmx");
        tiledMapRenderer = new TiledMapRendererWithEntities(tiledMap, 2);
        tiledMapRenderer.addEntity(character);
        MapLayer collisionObjectLayer = tiledMap.getLayers().get("objects");
        objects = collisionObjectLayer.getObjects();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(character.getLocation().x, character.getLocation().y, 0);
        camera.update();
        character.update(delta);
        System.out.println(character.getLocation());
        checkCollisionWithMap(character);

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public boolean checkCollisionWithMap(Entity entity) {
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (Intersector.overlaps(rectangle, entity.getBounds())) {
                entity.onCollisionWithObject(rectangleObject);
                return true;
            }
        }
        return false;
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
