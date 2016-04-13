package com.groeps33.valley.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Bram on 6-4-2016.
 */
public abstract class Entity {

    private String name;
    int currentHp;
    private int maxHp;
    private int defence;
    private int attackDamage;
    protected int attackSpeed;
    int moveSpeed;
    Vector2 location;
    private Vector2 velocity;

    Entity(float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed) {
        this.name = name;
        this.maxHp = maxHp;
        this.defence = defence;
        this.attackDamage = attackDamage;
        this.moveSpeed = moveSpeed;
        location = new Vector2(x,y);
        velocity = new Vector2();

        currentHp = maxHp;
    }

    public abstract void update(float deltaTime);

    public abstract void draw(Batch batch);

    public abstract Rectangle getBounds();

    public void onCollisionWithObject(MapObject object) {

    }

    void move(float x, float y) {
        location.add(x, y);
    }

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        setLocation(location.x, location.y);
    }

    public void setLocation(float x, float y) {
        this.location.set(x, y);
    }

    private int getGridX() {
        return Math.round(location.x / 32f);
    }

    private int getGridY() {
        return Math.round(location.y / 32f);
    }

    public Vector2 getGridLocation() {
        return new Vector2(getGridX(), getGridY());
    }
}
