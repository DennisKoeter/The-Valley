package com.groeps33.valley.highscore;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Edwin
 *         Created on 4/10/2016
 */
public class HighScoreTest {

    HighScore highscore;
    int maxPlayers = 5;

    @Before
    public void setUp() throws Exception {
        highscore = HighScore.getInstance();
    }

    @Test
    public void getInstance() throws Exception {
        highscore = HighScore.getInstance();
        assertNotNull("HighScore getinstance returns no highscore instance", highscore);
    }

    @Test
    public void clearBoard() throws Exception {
        highscore.generateBoard();
        highscore.clearBoard();
        int expectedAmountOfPlayers = 0;
        int actualAmountOfPlayers = highscore.getAmountOfPlayers();

        // I think this fails actually because you can't call assertEquals
        // on an integer as an integer doesn't override the equals method like
        // String does.
        // todo --> find another way to compare the two values
        assertEquals(expectedAmountOfPlayers, actualAmountOfPlayers);
    }

    @Test
    public void getAmountOfPlayers() throws Exception {
        int zeroSize = highscore.getAmountOfPlayers();
        highscore.generateBoard();
        int higherThanZero = highscore.getAmountOfPlayers();

        if (zeroSize >= higherThanZero) {
            fail("Generateboard did not generate a board containing at least one player.");
        }
    }

    @Test
    public void generateBoard() throws Exception {
        int zeroSize = highscore.getAmountOfPlayers();
        highscore.generateBoard();
        int higherThanZero = highscore.getAmountOfPlayers();

        if (zeroSize >= higherThanZero) {
            fail("Generateboard did not generate a board containing at least one player.");
        }
    }

}