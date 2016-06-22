package com.groeps33.valley.entity;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Edwin
 *         Created on 4/6/2016
 */
public class Projectile {
    private final Vector2 startLocation;
    private Vector2 location;
    private Vector2 velocity;
    private int damage;
    private double attackRange;

    public Vector2 getLocation() {
        return location;
    }

    public void setLocation(Vector2 location) {
        this.location = location;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Vector2 getStartLocation() {
        return startLocation;
    }

    public Projectile(Vector2 location, Vector2 velocity, int damage, int attackDamage) {
        this.startLocation = location;
        this.location = new Vector2(location);
        this.velocity = velocity;
        this.damage = damage;
        this.attackRange = attackDamage;
    }

    public double getAttackRange() {
        return attackRange;
    }
}
