package com.groeps33.valley.shop;

/**
 * Created by Robin on 6-4-2016.
 */

public enum Stat {
    DEFENCE(0),
    ATTACK_DAMAGE(1),
    ATTACK_SPEED(2),
    MOVE_SPEED(3),
    CURRENT_HP(4),
    MAX_HP(5);

    private int val;

    Stat(int val) {
        this.val = val;
    }

    public int val() {
        return val;
    }
}


