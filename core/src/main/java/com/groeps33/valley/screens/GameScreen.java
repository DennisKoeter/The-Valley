package com.groeps33.valley.screens;

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
import com.groeps33.valley.TheValleyGame;
import com.groeps33.valley.entity.Entity;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.net.GameServer;
import com.groeps33.valley.renderer.TiledMapRendererWithEntities;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class GameScreen extends TheValleyScreen {
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRendererWithEntities tiledMapRenderer;
    private Texture playerTexture;
    private Sprite playerSprite;
    private Character localPlayer;
    private MapObjects objects;
    private Map<String, Character> characters;
    private GameServer gameServer;
//    private SpriteBatch spriteBash;

    public GameScreen(TheValleyGame game) {
        super(game);
        characters = new ConcurrentHashMap<>();
    }

    @Override
    public void show() {
        //todo init
        game.setupNetworking(this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tiledMap = new TmxMapLoader().load("thevalley.tmx");
        tiledMapRenderer = new TiledMapRendererWithEntities(tiledMap, 5);
        System.out.println(tiledMapRenderer.getBatch());
        MapLayer collisionObjectLayer = tiledMap.getLayers().get("Collision");
        objects = collisionObjectLayer.getObjects();
        localPlayer = addPlayer(game.getUserAccount().getUsername());
        game.getGameClient().connect(localPlayer);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(localPlayer.getLocation().x, localPlayer.getLocation().y, 0);
        camera.update();

        float x = localPlayer.getLocation().x, y = localPlayer.getLocation().y;
        localPlayer.update(delta);
        if (x != localPlayer.getLocation().x || y != localPlayer.getLocation().y) {
            //has moved
            game.getGameClient().update(localPlayer);
        }

        checkCollisionWithMap(localPlayer);
        tiledMapRenderer.setView(camera);
        if (tiledMapRenderer.getBatch() != null) {
            tiledMapRenderer.render();
        } else {
            System.out.println("null");
        }
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
//        game.getGameClient().disconnect(localPlayer);
    }

    private boolean checkCollisionWithMap(Entity entity) {
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
            Rectangle rectangle = rectangleObject.getRectangle();
            if (Intersector.overlaps(rectangle, entity.getBounds())) {
                entity.onCollisionWithObject(rectangleObject);
                return true;
            }
        }
        return false;
    }

    public Character addPlayer(String username) {
        if (localPlayer != null && localPlayer.getName().equals(username)) {
            return localPlayer;
        }
        System.out.println("Player connected: " + username);
        Character character = new Character(90, 100, username);
        characters.put(username, character);
        tiledMapRenderer.addEntity(character);
        return character;
    }

    public void playerMoved(String username, float x, float y, byte direction) {
        Character character = characters.get(username);
        if (character == null) {
            throw new IllegalStateException("Player not found: " + username);
        }

        character.setLocation(x, y);
        character.updateFrame();
        character.setDirection(direction);
    }

    public void removePlayer(String username) {
        System.out.println("Player disconected: " + username);
        Character character = characters.get(username);
        if (character != null) {
            characters.remove(username);
            tiledMapRenderer.removeEntity(character);
        }
    }
}
