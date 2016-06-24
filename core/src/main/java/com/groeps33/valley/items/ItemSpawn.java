package com.groeps33.valley.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Edwin
 *         <p>
 *         An item spawn which can be found on the game map.
 */

public abstract class ItemSpawn {
    private Vector2 localtion;
    private int cooldown;


    public void draw(SpriteBatch spriteBatch) {

    }
}
