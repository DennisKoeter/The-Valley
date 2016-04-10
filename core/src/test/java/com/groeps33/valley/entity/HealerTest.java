package com.groeps33.valley.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Edwin
 *         Created on 4/10/2016
 */
public class HealerTest {

    private int locX = 10;
    private int locY = 10;
    private String name = "HealingBob";
    private int maxhp = 100;
    private int def = 25;
    private int ad = 10;
    private int ms = 15;
    private int healAmount = 10;
    private int healAmountForSet = 15;

    private Healer healer;

    @Before
    public void setUp() throws Exception {
        healer = new Healer(locX, locY, name, maxhp, def, ad, ms, healAmount);
    }

    @Test
    public void getHealAmount() throws Exception {
        assertEquals(healAmount, healer.getHealAmount());
    }

    @Test
    public void setHealAmount() throws Exception {
        healer.setHealAmount(healAmountForSet);
        assertEquals(healAmountForSet, healer.getHealAmount());
    }

}