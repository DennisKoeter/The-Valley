package com.groeps33.valley.entity;

/**
 * @author Edwin
 *         Created on 4/6/2016
 */
class Mage extends Character{

    private int fireBallDmg;

    public int getFireBallDmg() {
        return fireBallDmg;
    }

    public void setFireBallDmg(int fireBallDmg) {
        this.fireBallDmg = fireBallDmg;
    }

    public Mage(float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed, int fireBallDmg) {
        super(x, y, name, maxHp, defence, attackDamage, moveSpeed);
        this.fireBallDmg = fireBallDmg;
    }
}
