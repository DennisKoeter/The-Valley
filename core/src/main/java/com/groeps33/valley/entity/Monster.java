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

    private final Texture spriteSheet;
    private final TextureRegion[][] frames;
    private final Animation animation;
    private final long frameTime;
    private final TextureRegion currentFrame;
    private int width, height;
    private IntArray path;

    public Monster(float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed) {
        super(x, y, name, maxHp, defence, attackDamage, moveSpeed);
        spriteSheet = new Texture(Gdx.files.internal("sprites/Monsters.png"));
        frames = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 3, spriteSheet.getHeight() / 4);
        animation = new Animation(0.10f, frames[0][0]);
        frameTime = 0;
        currentFrame = animation.getKeyFrame(frameTime);
        this.width = currentFrame.getRegionWidth();
        this.height = currentFrame.getRegionHeight();
    }

    @Override
    public void update(float deltaTime) {
        //todo walk path
        int closestIdx = -1;
        double dist = Double.MAX_VALUE;
        if (path != null && path.size > 2) {
            for (int i = 0; i < path.size; i+=2) {
                double temp = Calculations.distance(path.get(i), path.get(i+1), getGridX(), getGridY());
                if (temp < dist) {
                    dist = temp;
                    closestIdx = i;
                }
            }
            System.out.println(closestIdx);
            if (closestIdx != path.size -2) {
                int nextX = path.get(closestIdx + 2) * 32;
                int nextY = path.get(closestIdx + 3) * 32;
                double angle = Math.atan2(nextY - location.y, nextX - location.x);
                move(moveSpeed * (float) Math.cos(angle) * deltaTime, moveSpeed * (float) Math.sin(angle) * deltaTime);
            }
        }

    }

    @Override
    public void draw(Batch batch) {
        batch.draw(currentFrame, location.x, location.y);
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
}
