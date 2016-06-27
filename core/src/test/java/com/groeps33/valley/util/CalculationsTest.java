package com.groeps33.valley.util;

import com.badlogic.gdx.math.Vector2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Edwin
 */
public class CalculationsTest {
    private Vector2 v1;
    private Vector2 v2;
    Calculations calc;
    @Before
    public void setUp() throws Exception {
    v1 = new Vector2(10,10);
        v2 = new Vector2(10,10);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void distance() throws Exception {
        assertEquals(0, calc.distance(v1.x, v1.y, v2.x, v2.y), 0.001);
    }

    @Test
    public void distance1() throws Exception {
        assertEquals(0, calc.distance(v1, v2), 0.001);
    }

}