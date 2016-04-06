package com.groeps33.valley.entity;

import com.badlogic.gdx.InputProcessor;
import com.groeps33.valley.shop.Consumable;
import com.groeps33.valley.shop.Statboost;
import com.groeps33.valley.shop.Stats;
import com.sun.xml.internal.bind.v2.runtime.IllegalAnnotationException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bram on 6-4-2016.
 */
public class Character extends Entity implements InputProcessor {

    private Consumable consumable;
    private int bonusAttackSpeed;
    private int bonusMoveSpeed;
    private int bonusHp;
    private int bonusDefence;
    private int bonusAttackDamage;
    private List<Statboost> statboosts;

    public Character(float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed) {
        super(x, y, name, maxHp, defence, attackDamage, moveSpeed);
        this.statboosts = new ArrayList<Statboost>();
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
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void setPosition(float x, float y) {
        location.set(x, y);
    }
}
