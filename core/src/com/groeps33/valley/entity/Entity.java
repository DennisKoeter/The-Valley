package com.groeps33.valley.entity;

import com.badlogic.gdx.math.Vector2;
import com.groeps33.valley.shop.Statboost;

/**
 * Created by Bram on 6-4-2016.
 */
public abstract class Entity {

    protected String name;
    protected int currentHp;
    protected int maxHp;
    protected int defence;
    protected int attackDamage;
    protected int attackSpeed;
    protected int moveSpeed;
    protected Vector2 location;
    protected Vector2 velocity;

    public Entity(float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed) {
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
}
