package com.groeps33.valley.shop;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 09/04/16.
 */
public class DoubloonShopTest {
    Statboost s1;
    Statboost s2;
    DoubloonShop shop;
    List<Statboost> expected;

    @Before
    public void setUp() throws Exception {
        s1 = new Statboost("health boost", Stat.MAX_HP, 100, 10, 5);
        s2 = new Statboost("attack boost", Stat.ATTACK_DAMAGE, 100, 10, 5);
        shop = new DoubloonShop();
        shop.addStatboost(s1);

        expected = new ArrayList<Statboost>();
        expected.add(s1);
    }

    @Test
    public void testConstructor() throws Exception {
        DoubloonShop test = new DoubloonShop();
        assertNotNull("object is null", test);
    }

    @Test
    public void testAddStatboost() throws Exception {
        expected.add(s2);
        shop.addStatboost(s2);

        assertEquals("lists are not equal", shop.getStatboosts(), expected);
    }

    @Test
    public void testGetStatboosts() throws Exception {
        assertEquals("lists are not equal", shop.getStatboosts(), expected);
    }

    @Test
    public void testFindStatboost() throws Exception {
        Statboost expectedStatboost = new Statboost("health boost", Stat.MAX_HP, 100, 10, 5);
        assertEquals("consumables are not equal", shop.findStatboost("health boost"), expectedStatboost);
    }

    @Test
    public void testBuyStatboost() throws Exception {
        //Todo
    }
}