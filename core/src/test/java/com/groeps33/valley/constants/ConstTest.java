package com.groeps33.valley.constants;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Edwin
 *         Created on 4/10/2016
 */
public class ConstTest {

    @Test
    public void getMaxPlayersAmount() throws Exception {
        assertNotNull(Const.HIGHSCORE_MAX_PLAYERS);
    }
}