package com.groeps33.valley.entity;

/**
 * @author Edwin
 *         Created on 4/6/2016
 */
public class Healer extends Character{

    private int healAmount;

    public int getHealAmount() {
        return healAmount;
    }

    public void setHealAmount(int healAmount) {
        this.healAmount = healAmount;
    }

    public Healer(float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed, int healAmount) {
        super(x, y, name, maxHp, defence, attackDamage, moveSpeed);
        this.healAmount = healAmount;
    }
}
