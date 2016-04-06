package com.groeps33.valley.entity;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Bram on 6-4-2016.
 */
public abstract class Entity {

    public Vector2 location;
    protected Vector2 velocity;

    public Entity() {
        location = new Vector2();
        velocity = new Vector2();
    }

    public abstract void update(float deltaTime);
}
