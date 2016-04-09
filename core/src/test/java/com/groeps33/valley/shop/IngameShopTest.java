package com.groeps33.valley.shop;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dennis on 09/04/16.
 */
public class IngameShopTest {
    Consumable c1;
    Consumable c2;
    IngameShop shop;
    List<Consumable> expected;

    @Before
    public void setUp() throws Exception {
        c1 = new Consumable("health potion", Stat.MAX_HP, 10, 100);
        c2 = new Consumable("attack potion", Stat.ATTACK_DAMAGE, 10, 100);
        shop = new IngameShop();
        shop.addConsumable(c1);

        expected = new ArrayList<Consumable>();
        expected.add(c1);
    }

    @Test
    public void testAddConsumable() throws Exception {
        expected.add(c2);
        shop.addConsumable(c2);

        assertEquals("lists are not equal", shop.getConsumables(), expected);
    }

    @Test
    public void testGetConsumables() throws Exception {
        assertEquals("lists are not equal", shop.getConsumables(), expected);
    }

    @Test
    public void testFindConsumable() throws Exception {
        Consumable expectedConsumable = new Consumable("health potion", Stat.MAX_HP, 10, 100);
        assertEquals("consumables are not equal", shop.findConsumable("health potion"), expectedConsumable);
    }

    @Test
    public void testBuyConsumable() throws Exception {
        //TODO
    }
}