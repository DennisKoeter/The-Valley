package com.groeps33.valley.entity;

/**
 * @author Edwin
 *         Created on 4/6/2016
 */
public class Mage extends Character{

    private int healAmount;

    /**
     * A type of character with the class Mage
     * @param x
     * @param y
     * @param name
     * @param maxHp
     * @param defence
     * @param attackDamage
     * @param moveSpeed
     */
    public Mage(float x, float y, String name, int maxHp, int defence, int attackDamage, int moveSpeed) {
        super(x, y, name, maxHp, defence, attackDamage, moveSpeed);
    }

}
