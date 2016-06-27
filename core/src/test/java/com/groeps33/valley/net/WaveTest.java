package com.groeps33.valley.net;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Edwin
 */
public class WaveTest {
    private Wave wave;
    @Before
    public void setUp() throws Exception {
    wave = new Wave(2, 500);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getNumber() throws Exception {
        assertEquals(2, wave.getNumber());
    }

    @Test
    public void getStartTime() throws Exception {
    assertEquals(500, wave.getStartTime());
    }

    @Test
    public void isFinished() throws Exception {

    }

    @Test
    public void getMonsterList() throws Exception {
    wave.checkSpawn();
        assertEquals(1, wave.getMonsterList().size());
    }

}