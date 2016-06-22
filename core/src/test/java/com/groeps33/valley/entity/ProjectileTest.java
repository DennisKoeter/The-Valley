package com.groeps33.valley.entity;

import com.badlogic.gdx.math.Vector2;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Edwin
 *         Created on 4/10/2016
 */
public class ProjectileTest {

    private Projectile projectile;
    private int locX = 5;
    private int locY = 10;
    private Vector2 location;
    private int loc2X = 5;
    private int loc2Y = 10;
    private Vector2 locationForSet;
    private int velX = 20;
    private int velY = 25;
    private Vector2 velocity;
    private int vel2X = 20;
    private int vel2Y = 25;
    private Vector2 velocityForSet;
    private int damage = 30;
    private int damageForSet = 35;

    @Before
    public void setUp() throws Exception {
        location = new Vector2(locX, locY);
        velocity = new Vector2(velX, velY);
        projectile = new Projectile(location, velocity, damage, attackDamage);
    }

    @Test
    public void getLocation() throws Exception {
        Vector2 actual = projectile.getLocation();
        assertEquals(location, actual);
    }

    @Test
    public void setLocation() throws Exception {
        locationForSet = new Vector2(loc2X, loc2Y);
        projectile.setLocation(locationForSet);

        assertEquals(locationForSet, projectile.getLocation());
    }

    @Test
    public void getVelocity() throws Exception {
        Vector2 actual = projectile.getVelocity();
        assertEquals(location, actual);
    }

    @Test
    public void setVelocity() throws Exception {
        velocityForSet= new Vector2(vel2X, vel2Y);
        projectile.setVelocity(velocityForSet);

        assertEquals(velocityForSet, projectile.getVelocity());
    }

    @Test
    public void getDamage() throws Exception {
        assertEquals(damage, projectile.getDamage());
    }

    @Test
    public void setDamage() throws Exception {
        projectile.setDamage(damageForSet);

        assertEquals(damageForSet, projectile.getDamage());
    }

}