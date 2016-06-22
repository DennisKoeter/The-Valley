package com.groeps33.valley.entity;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
public enum PlayerClass {
    ARCHER("sprites/archer.png", "sprites/archer projectile.png", 10, 0, 250, 200, 200, 750),
    WARRIOR("sprites/warrior.png", "sprites/warrior projectile.png", 10, 0, 250, 200, 150, 750);


    private final String spriteSheet;
    private final String projectileSpriteSheet;
    private final int attackDmg;
    private final int defence;
    private final int attackInterval; //250
    private final int attackRange; //100-200 ong
    private final int moveSpeed; //400
    private final int projectileSpeed; //500


    PlayerClass(String spriteSheet, String projectileSpriteSheet, int attackDmg, int defence, int attackInterval, int attackRange, int moveSpeed, int projectileSpeed) {
        this.spriteSheet = spriteSheet;
        this.projectileSpriteSheet = projectileSpriteSheet;
        this.attackDmg = attackDmg;
        this.defence = defence;
        this.attackInterval = attackInterval;
        this.attackRange = attackRange;
        this.moveSpeed = moveSpeed;
        this.projectileSpeed = projectileSpeed;
    }

    public String getSpriteSheet() {
        return spriteSheet;
    }

    public String getProjectileSpriteSheet() {
        return projectileSpriteSheet;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public int getDefence() {
        return defence;
    }

    public int getAttackInterval() {
        return attackInterval;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public int getMoveSpeed() {
        return moveSpeed;
    }

    public float getProjectileSpeed() {
        return projectileSpeed;
    }
}
