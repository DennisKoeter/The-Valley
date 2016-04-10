package com.groeps33.valley.entity;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Bram on 6-4-2016.
 */
public class Monster extends Entity {

    public Monster(float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed) {
        super(x, y, name, maxHp, defence, attackDamage, moveSpeed);
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void draw(Batch batch) {

    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
