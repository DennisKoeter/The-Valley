package com.groeps33.valley.shop;

/**
 * Created by Robin on 6-4-2016.
 */

public enum Stats {
    defence(0),
    attackDamage(1),
    attackSpeed(2),
    moveSpeed(3),
    currentHp(4),
    maxHp(5);

    private int val;

    Stats(int val) {
        this.val = val;
    }

    public int val() {
        return val;
    }
}


