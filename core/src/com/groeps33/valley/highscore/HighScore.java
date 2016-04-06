package com.groeps33.valley.highscore;

/**
 * @author Edwin
 *         Created on 4/6/2016
 */
public class HighScore {
    private static HighScore ourInstance = new HighScore();

    public static HighScore getInstance() {
        return ourInstance;
    }

    private HighScore() {
    }
}
