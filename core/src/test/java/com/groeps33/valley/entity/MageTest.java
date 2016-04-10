package com.groeps33.valley.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Edwin
 *         Created on 4/10/2016
 */
public class MageTest {

    private int locX = 10;
    private int locY = 10;
    private String name = "Blazing";
    private int maxhp = 100;
    private int def = 25;
    private int ad = 10;
    private int ms = 15;
    private int dmg = 10;
    private int dmgForSet = 15;

    private Mage mage;

    @Before
    public void setUp() throws Exception {
        mage = new Mage(locX, locY, name, maxhp, def, ad, ms, dmg);
    }

    @Test
    public void getFireBallDmg() throws Exception {
        assertEquals(dmg, mage.getFireBallDmg());
    }

    @Test
    public void setFireBallDmg() throws Exception {
        mage.setFireBallDmg(dmgForSet);
        assertEquals(dmgForSet, mage.getFireBallDmg());
    }

}