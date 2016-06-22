package com.groeps33.valley.renderer;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
public class Message {
    public enum Type { SERVER, PLAYER }
    private final String message;
    private final Type type;

    public Message(String message, Type type) {
        this.message = message;
        this.type = type;
    }
}
