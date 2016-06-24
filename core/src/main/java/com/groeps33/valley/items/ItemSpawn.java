package com.groeps33.valley.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Edwin
 *         <p>
 *         An item spawn which can be found on the game map.
 */

public abstract class ItemSpawn {
    private float x;
    private float y;
    private int cooldown;
    private Animation animation;
    private TextureRegion currentFrame;
    private TextureRegion[][] frames;
    private float frameTime;

    public ItemSpawn(Texture spriteSheet, int cooldown, float y, float x) {
        this.cooldown = cooldown;
        this.y = y;
        this.x = x;
        this.frames = TextureRegion.split(spriteSheet, spriteSheet.getWidth(), (spriteSheet.getHeight() / 2));
        animation = new Animation(0.25f,frames[0]);
        frameTime = 0f;
    }

    public void updateFrame() {
        if (frames != null) {
            frameTime += Gdx.graphics.getDeltaTime();
            currentFrame = animation.getKeyFrame(frameTime, true);
        }
    }
}
