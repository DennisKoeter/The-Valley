package com.groeps33.valley.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.math.Vector2;
import com.groeps33.valley.shop.Consumable;
import com.groeps33.valley.shop.Statboost;
import com.groeps33.valley.shop.Stats;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bram on 6-4-2016.
 */
public class Character extends Entity  {

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
    TextureRegion[][] frames;
    float frameTime;
    int previousDirection;

    public Character(float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed) {
        super(x, y, name, maxHp, defence, attackDamage, moveSpeed);
        this.statboosts = new ArrayList<Statboost>();
        previousDirection=4;
        //Wanneer meerdere karakters gebruikt worden kan
        // in de consructor een andere spritesheet geladen worden.
        spriteSheet = new Texture(Gdx.files.internal("sprites/character 1.png"));
        frames = TextureRegion.split(spriteSheet,spriteSheet.getWidth()/3,spriteSheet.getHeight()/4);
        animation = new Animation(0.10f,frames[0][0]);
        frameTime=0;
        currentFrame = animation.getKeyFrame(frameTime);
    }
    public int getGold(){
        return this.gold;
    }
    public void reduceGold(int amount){
        this.gold -= amount;
    }
    public boolean useConsumable() {
        if(this.consumable == null) return false;
        Stats stat = consumable.getStat();
        int boostValue = consumable.getBoost();

        switch (stat){
            case defence:
                this.bonusDefence += boostValue;
                break;
            case attackDamage:
                this.bonusAttackDamage += boostValue;
                break;
            case attackSpeed:
                this.bonusAttackSpeed += boostValue;
                break;
            case moveSpeed:
                this.bonusMoveSpeed += boostValue;
                break;
            case maxHp:
                this.bonusHp += boostValue;
                break;
            case currentHp:
                this.currentHp += boostValue;
                break;
            default:
                throw new IllegalArgumentException();
        }
        this.consumable = null;
        return true;
    }
    public void addStatboost(Statboost statboost){
        this.statboosts.add(statboost);
    }
    public void removeStatboost(Statboost statboost){
        this.statboosts.remove(statboost);
    }
    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }

    @Override
    public void update(float deltaTime) {



        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            if (previousDirection!=0) {
                previousDirection=0;
                frameTime = 0f;
                animation = new Animation(0.10f,frames[0]);
            }
            frameTime+=deltaTime;
            move(0,-moveSpeed);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            if (previousDirection!=1) {
                previousDirection=1;
                frameTime = 0f;
                animation = new Animation(0.10f,frames[1]);
            }
            frameTime+=deltaTime;
            move(-moveSpeed,0);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            if (previousDirection!=2) {
                previousDirection=2;
                frameTime = 0f;
                animation = new Animation(0.10f,frames[2]);
            }
            frameTime+=deltaTime;
            move(moveSpeed,0);
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            if (previousDirection!=3) {
                previousDirection=3;
                frameTime = 0f;
                animation = new Animation(0.10f,frames[3]);
            }
            frameTime+=deltaTime;
            move(0,moveSpeed);
        }

        currentFrame = animation.getKeyFrame(frameTime, true);

        if(currentFrame.isFlipY())
            currentFrame.flip(false,true);


    }

    public Vector2 getLocation()
    {
        return location;
    }

    public TextureRegion getCurrentFrame()
    {
        return currentFrame;
    }

    public void move(float x, float y)
    {
        location.add(x,y);
    }

    public void setPosition(float x, float y) {
        location.set(x, y);
    }
}
