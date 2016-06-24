package com.groeps33.valley.renderer;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.entity.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TiledMapRendererWithEntities extends OrthogonalTiledMapRenderer {
    private List<Entity> entities;
    private int drawEntitiesAfterLayer;

    public TiledMapRendererWithEntities(TiledMap map, int drawEntitiesAfterLayer) {
        super(map);
        this.drawEntitiesAfterLayer = drawEntitiesAfterLayer;
        entities = new CopyOnWriteArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    @Override
    public void render() {
        beginRender();
        int currentLayer = 0;
        for (MapLayer layer : map.getLayers()) {
            if (layer.isVisible()) {
                if (layer instanceof TiledMapTileLayer) {
                    renderTileLayer((TiledMapTileLayer) layer);
                    currentLayer++;
                    if (currentLayer == drawEntitiesAfterLayer) {
                        for (Entity entity : entities) {
                            entity.draw(this.getBatch());
                        }

                    }
                } else {
                    for (MapObject object : layer.getObjects()) {
                        renderObject(object);
                    }
                }
            }
        }

        endRender();
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
}