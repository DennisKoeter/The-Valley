package com.groeps33.valley.entity;

import com.badlogic.gdx.InputProcessor;
import com.groeps33.valley.shop.Consumable;
import com.groeps33.valley.shop.Statboost;

/**
 * Created by Bram on 6-4-2016.
 */
public class Character extends Entity implements InputProcessor {

    private Consumable consumable;

    public Character(float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed) {
        super(x, y, name, maxHp, defence, attackDamage, moveSpeed);
    }

    public boolean useConsumable() {
        if (consumable != null) {

            return true;
        }

        return false;
    }

    public void setConsumable(Consumable consumable) {
        this.consumable = consumable;
    }

    public void Consume(){
        if(this.consumable == null) return;
        //String stat = consumable.getStat();
        int boostValue = consumable.getBoost();

    }

    public void boostStat(Statboost statboost){

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
