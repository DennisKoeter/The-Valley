package com.groeps33.valley.entity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Edwin
 */
public class PlayerClassTest {
    private PlayerClass PLAYERCLASS = PlayerClass.ARCHER;
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getSpriteSheet() throws Exception {
        assertEquals("sprites/archer.png", PLAYERCLASS.getSpriteSheet());
    }

    @Test
    public void getProjectileSpriteSheet() throws Exception {
    assertEquals("sprites/archer projectile.png", PLAYERCLASS.getProjectileSpriteSheet());
    }

    @Test
    public void getAttackDmg() throws Exception {
    assertEquals(15, PLAYERCLASS.getAttackDmg());
    }

    @Test
    public void getDefence() throws Exception {
    assertEquals(0, PLAYERCLASS.getDefence());
    }

    @Test
    public void getAttackInterval() throws Exception {
    assertEquals(200, PLAYERCLASS.getAttackInterval());
    }

    @Test
    public void getAttackRange() throws Exception {
    assertEquals(250, PLAYERCLASS.getAttackRange());
    }

    @Test
    public void getMoveSpeed() throws Exception {
    assertEquals(300, PLAYERCLASS.getMoveSpeed());
    }

    @Test
    public void getProjectileSpeed() throws Exception {
        double speedactual = PLAYERCLASS.getProjectileSpeed();
        double speedexpected = 750;
    assertEquals(speedexpected, speedactual, 0.001);
    }

    @Test
    public void getProjectileSize() throws Exception {
    assertEquals(10, PLAYERCLASS.getProjectileSize());
    }

}