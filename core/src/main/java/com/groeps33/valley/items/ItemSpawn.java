package com.groeps33.valley.items;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

/**
 * @author Edwin
 *         <p>
 *         An item spawn which can be found on the game map.
 */

public abstract class ItemSpawn {
    private final float y, x;
    private Vector2 localtion;
    private int cooldown;


    public void draw(SpriteBatch spriteBatch) {

    }

    private Animation animation;
    private TextureRegion currentFrame;
    private TextureRegion[][] blueframes;
    private TextureRegion[][] redframes;
    private TextureRegion[][] greenframes;
    private TextureRegion[][] orangeframes;
    private TextureRegion[][] pinkframes;
    private TextureRegion[][] purpleframes;
    private TextureRegion[][] frames;
    private float frameTime;
    private Texture emptySpawn;
    private int type;

    BitmapFont font;

    public ItemSpawn(float y, float x) {
        this.cooldown = 0;
        this.y = y;
        this.x = x;

        blueframes = TextureRegion.split(new Texture(Gdx.files.internal("pickups/blue.png")),34,34);
        redframes = TextureRegion.split(new Texture(Gdx.files.internal("pickups/blue.png")),34,34);
        greenframes = TextureRegion.split(new Texture(Gdx.files.internal("pickups/blue.png")),34,34);
        orangeframes = TextureRegion.split(new Texture(Gdx.files.internal("pickups/blue.png")),34,34);
        pinkframes = TextureRegion.split(new Texture(Gdx.files.internal("pickups/blue.png")),34,34);
        purpleframes = TextureRegion.split(new Texture(Gdx.files.internal("pickups/blue.png")),34,34);
        font = new BitmapFont();
        this.emptySpawn = new Texture(Gdx.files.internal("pickups/empty.png"));


        frameTime = 0f;
    }

    public void updateFrame() {
        if (cooldown == 0) {
            if (frames != null) {
                frameTime += Gdx.graphics.getDeltaTime();
                animation = new Animation(0.25f,frames[type]);
                currentFrame = animation.getKeyFrame(frameTime, true);
            }
        } else {
            currentFrame.setRegion(emptySpawn);
            cooldown-=Gdx.graphics.getDeltaTime();
        }

    }

    public void pickUp() {
        this.cooldown = 60;
    }

    public void nextPickUp(){
        type = new Random().nextInt(6);
        switch(type) {
            case 0:
                frames = blueframes;
                break;
            case 1:
                frames = redframes;
                break;
            case 2:
                frames = greenframes;
                break;
            case 3:
                frames = orangeframes;
                break;
            case 4:
                frames = pinkframes;
                break;
            case 5:
                frames = purpleframes;
                break;
        }
    }

    public void draw(Batch batch) {
        batch.draw(currentFrame, x, y);
        font.draw(batch, String.valueOf(type), x, y + 50);

    }
}
