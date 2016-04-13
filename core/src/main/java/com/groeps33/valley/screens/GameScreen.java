package com.groeps33.valley.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.IntArray;
import com.groeps33.valley.ai.Astar;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.entity.Entity;
import com.groeps33.valley.entity.Monster;
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
    private Monster monster;
//    private SpriteBatch spriteBash;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        character = new Character(85, 3000, null, 0, 0, 0, 200);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tiledMap = new TmxMapLoader().load("Map 2.tmx");
        tiledMapRenderer = new TiledMapRendererWithEntities(tiledMap, 2);
        tiledMapRenderer.addEntity(character);

        monster = new Monster(400, 2800, null, -1, -1, -1, 100);
        tiledMapRenderer.addEntity(monster);

        MapLayer collisionObjectLayer = tiledMap.getLayers().get("objects");
        objects = collisionObjectLayer.getObjects();
    }

    private int lastGirdX;
    private int lastGridY;
    int lastMonsterGridX;
    int lastMonsterGridY;

    private IntArray path;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(character.getLocation().x, character.getLocation().y, 0);
        camera.update();
        character.update(delta);
        generatePathForMonster();
        monster.update(delta);

        checkCollisionWithMap(character);
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        //render pad voor demo
        if (path != null) {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setProjectionMatrix(camera.combined);
            for (int i = 2; i < path.size; i += 2) {
                Color color = Color.DARK_GRAY;
                color.a = 0.5f;
                shapeRenderer.setColor(color);
                shapeRenderer.rect(path.get(i) * 32, path.get(i + 1) * 32, 32, 32);
            }
            shapeRenderer.end();
            Gdx.gl.glDisable(GL20.GL_BLEND);
        }

        checkCollisionWithMonster();
    }

    private void checkCollisionWithMonster() {
        if (Intersector.overlaps(character.getBounds(), monster.getBounds())) {
            character.setLocation(85, 3000);
            monster.setLocation(400, 2800);
        }
    }

    private void generatePathForMonster() {
        int gridX = (int) (character.getLocation().x / 32);
        int gridY = (int) (character.getLocation().y / 32);
        final int monsterGridX = (int) (monster.getLocation().x / 32);
        final int monsterGridY = (int) (monster.getLocation().y / 32);
        if (lastGirdX != gridX || lastGridY != gridY) {
            path = new Astar(200, 200) {
                @Override
                public boolean isValid(int gridX, int gridY) {
                    if (gridX == monsterGridX && gridY == monsterGridY)
                        return true;

                    Rectangle tile = new Rectangle(gridX * 32, gridY * 32, 32, 32);
                    for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
                        Rectangle rectangle = rectangleObject.getRectangle();
                        if (Intersector.overlaps(rectangle, tile)) {
                            return false;
                        }
                    }
                    return true;
                }
            }.getPath(gridX, gridY, monsterGridX, monsterGridY);
            lastGridY = gridY;
            lastGirdX = gridX;

            for (int i = 0; i < path.size; i++) {
                path.set(i, path.get(i) * 32);
            }
            path.add((int) character.getLocation().x);
            path.add((int) character.getLocation().y);
            monster.setPath(path);
        }
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
