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
    Stat stat = Stat.MAX_HP;
    int boost = 10;
    int cost = 100;

    @Before
    public void setUp() throws Exception {
        c = new Consumable(name, stat, boost, cost);
    }

    @Test
    public void testConstructor() throws Exception {
        Consumable test = new Consumable("test", Stat.ATTACK_DAMAGE, 1, 1);
        assertNotNull("object is null", test);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("names are not equal", c.getName(), name);
    }

    @Test
    public void testGetStat() throws Exception {
        assertEquals("stats are not equal", c.getStat(), stat);
    }

    @Test
    public void testGetBoost() throws Exception {
        assertEquals("boosts are not equal", c.getBoost(), boost);
    }

    @Test
    public void testGetCost() throws Exception {
        assertEquals("costs are not equal", c.getCost(), cost);
    }
}