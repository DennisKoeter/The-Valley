package com.groeps33.valley.highscore;


import com.groeps33.valley.constants.Const;

/**
 * @author Edwin
 *         Created on 4/6/2016
 */
public class HighScore {

    private int maximumPlayers;

    private static HighScore ourInstance = new HighScore(Const.HIGHSCORE_MAX_PLAYERS);

    public static HighScore getInstance() {
        return ourInstance;
    }

    private HighScore(int maximumPlayers) {
        this.maximumPlayers = maximumPlayers;
    }
}
