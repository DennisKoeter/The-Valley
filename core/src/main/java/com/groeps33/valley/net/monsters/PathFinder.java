package com.groeps33.valley.net.monsters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.IntArray;
import com.groeps33.valley.ai.Astar;
import com.groeps33.valley.entity.Monster;
import com.groeps33.valley.entity.Character;

/**
 * Created by Bram on 6/24/2016.
 *
 * @author Bram Hoendervangers
 */
public class PathFinder {
    private final MapObjects objects;
    private final int height;
    private final int width;
    private final int tileWidth;
    private final int tileHeight;

    public PathFinder() {
        TiledMap tiledMap = new TmxMapLoader().load("thevalley.tmx");
        MapProperties mapProperties = tiledMap.getProperties();
        this.height = mapProperties.get("height", Integer.class);
        this.width = mapProperties.get("width", Integer.class);
        this.tileWidth = mapProperties.get("tilewidth", Integer.class);
        this.tileHeight = mapProperties.get("tileheight", Integer.class);

        MapLayer collisionObjectLayer = tiledMap.getLayers().get("Collision");
        objects = collisionObjectLayer.getObjects();
    }

    public void generatePathForMonster(Monster monster, Character target) {
        int gridX = (int) (target.getLocation().x / tileWidth);
        int gridY = (int) (target.getLocation().y / tileHeight);
        final int monsterGridX = (int) (monster.getLocation().x / tileWidth);
        final int monsterGridY = (int) (monster.getLocation().y / tileHeight);
//        if (lastGirdX != gridX || lastGridY != gridY) {
        IntArray path = new Astar(width, height) {
            @Override
            public boolean isValid(int gridX, int gridY) {
                if (gridX == monsterGridX && gridY == monsterGridY)
                    return true;

                Rectangle tile = new Rectangle(gridX * tileWidth, gridY * tileHeight, tileWidth, tileHeight);
                for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
                    Rectangle rectangle = rectangleObject.getRectangle();
                    if (Intersector.overlaps(rectangle, tile)) {
                        return false;
                    }
                }
                return true;
            }
        }.getPath(gridX, gridY, monsterGridX, monsterGridY);
//            lastGridY = gridY;
//            lastGirdX = gridX;

        for (int i = 0; i < path.size; i++) {
            path.set(i, path.get(i) * 32); //todo add supp for diff widht and heights for tiles
        }

        path.add((int) target.getLocation().x);
        path.add((int) target.getLocation().y);
        monster.setPath(path);
    }
}

