package com.groeps33.valley.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.groeps33.server.shared.game.IGameClient;
import com.groeps33.valley.shop.Consumable;
import com.groeps33.valley.shop.Statboost;
import com.groeps33.valley.shop.Stat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bram on 6-4-2016.
 */
public class Character extends Entity {
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(byte ordinal) {
       setDirection(Direction.values()[ordinal]);
    }

    public enum Direction {SOUTH, WEST, EAST, NORTH}

    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;
    private Consumable consumable;
    private int bonusAttackSpeed;
    private int bonusMoveSpeed;
    private int bonusHp;
    private int bonusDefence;
    private int bonusAttackDamage;
    private int gold;
    private List<Statboost> statboosts;

    private Animation animation;
    private Texture spriteSheet;
    private TextureRegion currentFrame;
    private TextureRegion[][] frames;
    private float frameTime;
    private Direction direction;

    private BitmapFont font;

    public Character(float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed) {
        super(x, y, name, maxHp, defence, attackDamage, moveSpeed);
        this.statboosts = new ArrayList<Statboost>();
        direction = Direction.SOUTH;
        //Wanneer meerdere karakters gebruikt worden kan
        // in de consructor een andere spritesheet geladen worden.
        spriteSheet = new Texture(Gdx.files.internal("sprites/character 1.png"));
        frames = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / 3, spriteSheet.getHeight() / 4);
        animation = new Animation(0.10f, frames[0][0]);
        frameTime = 0;
        currentFrame = animation.getKeyFrame(frameTime);
        font = new BitmapFont();
    }

    public int getGold() {
        return this.gold;
    }

    public void reduceGold(int amount) {
        this.gold -= amount;
    }

    public boolean useConsumable() {
        if (this.consumable == null) return false;
        Stat stat = consumable.getStat();
        int boostValue = consumable.getBoost();

        switch (stat) {
            case DEFENCE:
                this.bonusDefence += boostValue;
                break;
            case ATTACK_DAMAGE:
                this.bonusAttackDamage += boostValue;
                break;
            case ATTACK_SPEED:
                this.bonusAttackSpeed += boostValue;
                break;
            case MOVE_SPEED:
                this.bonusMoveSpeed += boostValue;
                break;
            case MAX_HP:
                this.bonusHp += boostValue;
                break;
            case CURRENT_HP:
                this.currentHp += boostValue;
                break;
            default:
                throw new IllegalArgumentException();
        }
        this.consumable = null;
        return true;
    }

    public void addStatboost(Statboost statboost) {
        this.statboosts.add(statboost);
    }

    public void removeStatboost(Statboost statboost) {
        this.statboosts.remove(statboost);
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }


    private void setDirection(Direction direction) {
        if (this.direction != direction) {
            this.direction = direction;
            frameTime = 0f;
            animation = new Animation(0.10f, frames[direction.ordinal()]);
        }
    }

    @Override
    public void update(float deltaTime) {

        velocity.set(0,0);
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            velocity.x = deltaTime * -moveSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            velocity.x = deltaTime * moveSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            velocity.y = deltaTime * moveSpeed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            velocity.y = deltaTime * -moveSpeed;
        }

        if (velocity.y != 0) {
            if (velocity.y > 0) {
                setDirection(Direction.NORTH);
            } else {
                setDirection(Direction.SOUTH);
            }
        } else if (velocity.x > 0) {
            setDirection(Direction.EAST);
        } else {
            setDirection(Direction.WEST);
        }

        if (velocity.x != 0 || velocity.y != 0) {
            frameTime += deltaTime;
            currentFrame = animation.getKeyFrame(frameTime, true);

            if (currentFrame.isFlipY())
                currentFrame.flip(false, true);

            move();
        }
    }

    /*
    private void move() {
        /*
        switch (direction) {
            case NORTH:
                move(0, moveSpeed * Gdx.graphics.getDeltaTime());
                break;
            case SOUTH:
                move(0, -moveSpeed * Gdx.graphics.getDeltaTime());
                break;
            case EAST:
                move(moveSpeed * Gdx.graphics.getDeltaTime(), 0);
                break;
            case WEST:
                move(-moveSpeed * Gdx.graphics.getDeltaTime(), 0);
        }
    }

        */

    @Override
    public void onCollisionWithObject(MapObject object) {
        /*
        switch (direction) {
            case NORTH:
                velocity.set(0, -moveSpeed * Gdx.graphics.getDeltaTime());
                break;
            case SOUTH:
                velocity.set(0, moveSpeed * Gdx.graphics.getDeltaTime());
                break;
            case EAST:
                velocity.set(-moveSpeed * Gdx.graphics.getDeltaTime(), 0);
                break;
            case WEST:
                velocity.set(moveSpeed * Gdx.graphics.getDeltaTime(), 0);
        }
        */
        velocity.set(-velocity.x, -velocity.y);
        move();
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(currentFrame, location.x, location.y);
        font.draw(batch, name, location.x, location.y + 50);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(location.x, location.y, WIDTH, HEIGHT);
    }

    public Vector2 getLocation() {
        return location;
    }

    public Animation getCharacterAnimator() {
        return animation = new Animation(0.10f , frames[0]);
    }
}
