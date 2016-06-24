package com.groeps33.valley.entity;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    private int width = 32;
    private int height = 32;

    private int id = 1;

    @After
    public void tearDown() throws Exception {

    }

    @Before
    public void setUp() throws Exception {
        monster = new Monster(id, locX, locY, name, maxHp, defence, ad, ms);
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void draw() throws Exception {

    }

    @Test
    public void getBounds1() throws Exception {
        Vector2 locationTest;
        locationTest = new Vector2(10,10);
        int WIDTH = 32;
        int HEIGHT = 32;
        monster.setLocation(10,10);
        Rectangle rectest = new Rectangle(locationTest.x, locationTest.y, WIDTH, HEIGHT);
        assertEquals("is hetzelfde", monster.getBounds(), rectest );
    }

    @Test
    public void setPath() throws Exception {

    }

    @Test
    public void getPath() throws Exception {

    }

    @Test
    public void setTarget() throws Exception {
    monster.setTarget("harrie");
        assertEquals("harrie", monster.getTarget());
    }

    @Test
    public void getTarget() throws Exception {
assertEquals("harrie", monster.getTarget());
    }

    @Test
    public void getId() throws Exception {
    assertEquals(1, monster.getId());
    }

    @Test
    public void getBounds() throws Exception {
        Rectangle actual = monster.getBounds();
        Rectangle expected = new Rectangle(locX, locY, width, height);

        assertEquals(actual, expected);
    }

}