package com.groeps33.server.shared.lobby.exceptions;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public class MaximumPlayerException extends Exception {
    public MaximumPlayerException() {
        super("Maximum players must be more or equal to one.");
    }
}
