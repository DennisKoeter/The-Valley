package com.groeps33.valley.entity;

import com.badlogic.gdx.math.Rectangle;
import org.junit.After;
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

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void draw() throws Exception {

    }

    @Test
    public void getBounds1() throws Exception {

    }

    @Test
    public void setPath() throws Exception {

    }

    @Test
    public void getPath() throws Exception {

    }

    @Test
    public void setTarget() throws Exception {

    }

    @Test
    public void getTarget() throws Exception {

    }

    @Test
    public void getId() throws Exception {

    }



    @Before
    public void setUp() throws Exception {
        monster = new Monster(id, locX, locY, name, maxHp, defence, ad, ms);
    }

    @Test
    public void getBounds() throws Exception {
        Rectangle actual = monster.getBounds();
        Rectangle expected = new Rectangle(locX, locY, width, height);

        assertEquals(actual, expected);
    }

}