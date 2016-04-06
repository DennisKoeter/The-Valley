package com.groeps33.valley.entity;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Bram on 6-4-2016.
 */
public abstract class Entity {

    private Vector2 location;
    private Vector2 velocity;

    public abstract void update(float deltaTime);
}
