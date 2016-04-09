package com.groeps33.valley.shop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 09/04/16.
 */
public class ConsumableTest {
    Consumable c;
    String name = "health potion";
    Stats stat = Stats.maxHp;
    int boost = 10;
    int cost = 100;

    @Before
    public void setUp() throws Exception {
        c = new Consumable(name, stat, boost, cost);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("names are not equal", c.getName(), name);
    }

    @Test
    public void testGetStat() throws Exception {

    }

    @Test
    public void testGetBoost() throws Exception {

    }

    @Test
    public void testGetCost() throws Exception {

    }
}