package com.groeps33.valley.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.IntArray;
import com.groeps33.valley.util.Calculations;

/**
 * Created by Bram on 6-4-2016.
 */
public class Monster extends Entity {

    private Texture spriteSheet;
    private TextureRegion[][] frames;
    private Animation animation;
    private long frameTime;
    private TextureRegion currentFrame;
    private final int id;
    private int width, height;
    private IntArray path;
    private String target;
    private long lastTargetCheck;
    private int damage;
    private long lastAtkTime;

    public Monster(int id, float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed) {
        super(x, y, name, maxHp, defence, attackDamage, moveSpeed, 2);
        this.id = id;

    }

    @Override
    public void update(float deltaTime) {
        //todo walk path
        int closestIdx = -1;
        double dist = Double.MAX_VALUE;
        if (path != null && path.size > 2) {
            for (int i = 0; i < path.size; i += 2) {
                double temp = Calculations.distance(path.get(i), path.get(i + 1), (int) location.x, (int) location.y);
                if (temp < dist) {
                    dist = temp;
                    closestIdx = i;
                }
            }

            if (closestIdx != path.size - 2) {
                int nextX = path.get(closestIdx + 2);
                int nextY = path.get(closestIdx + 3);
                double angle = Math.atan2(nextY - location.y, nextX - location.x);
                velocity.set(moveSpeed * (float) Math.cos(angle) * deltaTime, moveSpeed * (float) Math.sin(angle) * deltaTime);
                move();
            } else {
                int nextX = path.get(closestIdx);
                int nextY = path.get(closestIdx + 1);
                if (Calculations.distance(nextX, nextY, (int)location.x, (int)location.y) < 3) {
                    path = null;
                } else {
                    double angle = Math.atan2(nextY - location.y, nextX - location.x);
                    velocity.set(moveSpeed * (float) Math.cos(angle) * deltaTime, moveSpeed * (float) Math.sin(angle) * deltaTime);
                    move();
                }
            }
        }

    }

    @Override
    public void draw(Batch batch) {
        if (spriteSheet == null) {
            init();
        }
        batch.draw(currentFrame, location.x, location.y);
    }

    private void init() {
        spriteSheet = new Texture(Gdx.files.internal("sprites/monsters.png"));
        frames = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 3, spriteSheet.getHeight() / 4);
        animation = new Animation(0.10f, frames[0][0]);
        frameTime = 0;
        currentFrame = animation.getKeyFrame(frameTime);
        this.width = currentFrame.getRegionWidth();
        this.height = currentFrame.getRegionHeight();
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(location.x, location.y, 32, 32);
    }

    public void setPath(IntArray path) {
        this.path = path;
    }

    public IntArray getPath() {
        return path;
    }

    public void setTarget(String target) {
        this.target = target;
        lastTargetCheck = System.currentTimeMillis();
    }

    public String getTarget() {
        return target;
    }

    public int getId() {
        return id;
    }

    public long getLastTargetCheck() {
        return lastTargetCheck;
    }

    public int getDamage() {
        return damage;
    }

    public boolean canAttack() {
        return System.currentTimeMillis() - lastAtkTime > 1000;
    }

    public void attacked() {
        lastAtkTime = System.currentTimeMillis();
    }
}
