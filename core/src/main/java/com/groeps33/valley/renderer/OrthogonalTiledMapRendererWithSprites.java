package com.groeps33.valley.renderer;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.groeps33.valley.entity.Character;

import java.util.ArrayList;
import java.util.List;

public class OrthogonalTiledMapRendererWithSprites extends OrthogonalTiledMapRenderer {
        private List<Sprite> sprites;
        private  List<Character> characters;
        private int drawSpritesAfterLayer = 1;
        SpriteBatch spriteBash;

        public OrthogonalTiledMapRendererWithSprites(TiledMap map) {
            super(map);
            characters=new ArrayList<Character>();
            spriteBash = new SpriteBatch();

            sprites = new ArrayList<Sprite>();
        }

        public void addSprite(Sprite sprite){
            sprites.add(sprite);
        }

        public void addCharacter(Character character) {
            characters.add(character);

        }

        @Override
        public void render() {
            beginRender();
            int currentLayer = 0;
            for (MapLayer layer : map.getLayers()) {
                if (layer.isVisible()) {
                    if (layer instanceof TiledMapTileLayer) {
                        renderTileLayer((TiledMapTileLayer)layer);
                        currentLayer++;
                        if(currentLayer == drawSpritesAfterLayer){
                            for(Sprite sprite : sprites)
                                sprite.draw(this.getBatch());
                            for(Character character:characters){
                                //TODO Wanner character hier wordt gedrawed dus tussen de layers komt er geen map.
                                spriteBash.begin();
                                spriteBash.draw(character.getCurrentFrame(),character.getLocation().x,character.getLocation().y);
                                spriteBash.end();
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
    }