package com.groeps33.valley.entity;

import com.badlogic.gdx.math.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Edwin
 *         Created on 4/10/2016
 */
public class MonsterTest {

    private int locX = 10;
    private int locY = 15;
    private String name = "Blazeit";
    private int maxHp = 100;
    private int defence = 25;
    private int ad = 10;
    private int ms = 5;
    private Monster monster;

    private int width = 5;
    private int height = 5;

    private int id = 1;

    @Before
    public void setUp() throws Exception {
        monster = new Monster(id, locX, locY, name, maxHp, defence, ad, ms);
    }

    @Test
    public void getBounds() throws Exception {
        Rectangle actual =  monster.getBounds();
        Rectangle expected = new Rectangle(locX, locY, width, height);

        assertEquals(actual, expected);
    }

}