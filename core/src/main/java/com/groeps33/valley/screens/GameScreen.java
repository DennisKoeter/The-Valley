package com.groeps33.valley.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.math.Vector2;
import com.groeps33.valley.TheValleyGame;
import com.groeps33.valley.entity.Entity;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.entity.PlayerClass;
import com.groeps33.valley.entity.Projectile;
import com.groeps33.valley.net.GameServer;
import com.groeps33.valley.renderer.HudRenderer;
import com.groeps33.valley.renderer.Message;
import com.groeps33.valley.renderer.TiledMapRendererWithEntities;
import com.groeps33.valley.util.Calculations;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class GameScreen extends TheValleyScreen {
    private static final Vector2 START_LOC = new Vector2(90, 100);
    private final PlayerClass playerClass;
    private TiledMap tiledMap;
    private OrthographicCamera camera;
    private TiledMapRendererWithEntities tiledMapRenderer;
    private Texture playerTexture;
    private Sprite playerSprite;
    private Character localPlayer;
    private MapObjects objects;
    private Map<String, Character> characters;
    private GameServer gameServer;
    private HudRenderer hudRenderer;

//    private SpriteBatch spriteBash;

    public GameScreen(TheValleyGame game, PlayerClass playerClass) {
        super(game);
        characters = new ConcurrentHashMap<>();
        hudRenderer = new HudRenderer(this);
        this.playerClass = playerClass;
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
        localPlayer = addPlayer(game.getUserAccount().getUsername(), playerClass);
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

        updateProjectiles(delta);

        checkCollisionWithMap(localPlayer);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        hudRenderer.render();

        boolean hasProjectiles = !localPlayer.getProjectiles().isEmpty();
        checkCollisionWithLocalProjectiles();

        if (localPlayer.getCurrentHp() <= 0) {
            localPlayer.setLocation(START_LOC.x, START_LOC.y);
            localPlayer.resetHp();
        }

        if (x != localPlayer.getLocation().x || y != localPlayer.getLocation().y || !localPlayer.getProjectiles().isEmpty()
    || (hasProjectiles && localPlayer.getProjectiles().isEmpty())) {
            game.getGameClient().update(localPlayer);
        }
    }

    private void checkCollisionWithLocalProjectiles() {
        Iterator<Projectile> it = localPlayer.getProjectiles().iterator();
        while (it.hasNext()) {
            Projectile projectile = it.next();
            Rectangle bounds = new Rectangle(projectile.getLocation().x-5, projectile.getLocation().y-5, 10, 10);
            for (Character character: characters.values()) {
                if (character != localPlayer && Intersector.overlaps(character.getBounds(), bounds)) {
                    game.getGameClient().updatePlayerHit(localPlayer, character, projectile);
                    it.remove();
                }
            }
        }
    }

    private void updateProjectiles(float deltaTime) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT) && localPlayer.canAttack()) {
            Projectile projectile = localPlayer.spawnProjectile();
//            game.getGameClient().spawn(projectile);
        }

        Iterator<Projectile> it = localPlayer.getProjectiles().iterator();
        while (it.hasNext()) {
            Projectile projectile = it.next();
            if (Calculations.distance(projectile.getLocation(), projectile.getStartLocation()) >= projectile.getAttackRange()) {
                it.remove();
//                game.getGameClient().remove(projectile);
            }


            System.out.println(projectile);
            projectile.getLocation().add(projectile.getVelocity().x * deltaTime, projectile.getVelocity().y * deltaTime);
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

    public Character addPlayer(String username, PlayerClass playerClass) {
        if (localPlayer != null && localPlayer.getName().equals(username)) {
            return localPlayer;
        }
        hudRenderer.addMessage(new Message("Player connected: " + username, Message.Type.SERVER));
        Character character = new Character(START_LOC.x, START_LOC.y, username, playerClass);
        characters.put(username, character);
        tiledMapRenderer.addEntity(character);
        return character;
    }

    public void playerMoved(String username, float x, float y, byte direction) {
        Character character = characters.get(username);
        if (character == null) {
            throw new IllegalStateException("Player not found: " + username);
//            character = addPlayer(username,);
        }

        character.setLocation(x, y);
        character.updateFrame();
        character.setDirection(direction);
    }

    public void removePlayer(String username) {
         hudRenderer.addMessage(new Message("Player disconnected: " + username, Message.Type.SERVER));
        Character character = characters.get(username);
        if (character != null) {
            characters.remove(username);
            tiledMapRenderer.removeEntity(character);
        }
    }

    public Character getLocalPlayer() {
        return localPlayer;
    }

    public void setProjectiles(String username, float[] projectileX, float[] projectileY) {
        Character character = characters.get(username);
        if (character == null) {
            throw new IllegalStateException("Player not found: " + username);
//            character = addPlayer(username, connectPacket.getPlayerClass());
        }

        int i = 0;
        for (;i < Math.min(character.getProjectiles().size(), projectileX.length); i++) {
            character.getProjectiles().get(i).getLocation().set(projectileX[i], projectileY[i]);
        }

        if (i >= projectileX.length) {
            for (int j = projectileX.length-1; j < character.getProjectiles().size(); j++) {
                if (j == -1) continue;
                character.getProjectiles().remove(j);
            }
        } else {
            for (int j = i; j < projectileX.length; j++) {
                character.getProjectiles().add(new Projectile(new Vector2(projectileX[j], projectileY[j]), null, -1, -1));
            }
        }
    }

    public void registerHit(String hitByPlayer, int damage) {
        hudRenderer.addMessage(new Message("Friendly fire by " + hitByPlayer, Message.Type.FRIENDLY_FIRE));
        localPlayer.damage(damage);
    }

    public void registerNewWave(int number) {

    }
}
