package com.groeps33.valley.shop;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 09/04/16.
 */
public class StatboostTest {
    private Statboost statboost;
    String name = "health boost";
    Stat stat = Stat.MAX_HP;
    int cost = 100;
    int boost = 10;
    int duration = 5;

    @Before
    public void setUp() throws Exception {
        statboost = new Statboost(name, stat, cost, boost, duration);
    }

    @Test
    public void testConstructor() throws Exception {
        Statboost test = new Statboost("test", Stat.ATTACK_DAMAGE, 1, 1, 1);
        assertNotNull("object is null", test);
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("names are not equal", statboost.getName(), name);
    }

    @Test
    public void testGetStat() throws Exception {
        assertEquals("stats are not equal", statboost.getStat(), stat);
    }

    @Test
    public void testGetBoost() throws Exception {
        assertEquals("boosts are not equal", statboost.getBoost(), boost);
    }

    @Test
    public void testGetDuration() throws Exception {
        assertEquals("durations are not equal", statboost.getDuration(), duration);
    }

    @Test
    public void testGetCost() throws Exception {
        assertEquals("costs are not equal", statboost.getCost(), cost);
    }

    @Test
    public void testReduceDurationPositive() throws Exception {
        statboost.reduceDuration(1);
        assertEquals("durations are not equal", statboost.getDuration(), duration - 1);
    }

    @Test
    public void testReduceDurationNegative() throws Exception {
        statboost.reduceDuration(-1);
        assertEquals("durations are not equal", statboost.getDuration(), duration + 1);
    }
}