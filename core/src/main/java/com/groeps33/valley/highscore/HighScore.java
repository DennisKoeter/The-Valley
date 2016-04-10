package com.groeps33.valley.highscore;


import com.groeps33.valley.constants.Const;
import com.groeps33.valley.entity.Character;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * @author Edwin
 *         Created on 4/6/2016
 */
public class HighScore {

    /**
     * Maximum players on the highscore board at a time
     */
    private int maximumPlayers;
    private List<Character> characterList;

    private static HighScore ourInstance = new HighScore(Const.HIGHSCORE_MAX_PLAYERS);

    public static HighScore getInstance() {
        return ourInstance;
    }

    private HighScore(int maximumPlayers) {
        this.maximumPlayers = maximumPlayers;
    }

    /**
     * Returns an integer displaying the size of all characters in this highscore board
     * @return size of highscore board
     */
    public int getAmountOfPlayers() {
        return characterList.size();
    }

    /**
     * Clears the current board
     */
    public void clearBoard() {
        throw new NotImplementedException();
    }

    /**
     * Generate a new HighScore board based on character level/experience
     */
    public void generateBoard() {
        throw new NotImplementedException();
    }
}
