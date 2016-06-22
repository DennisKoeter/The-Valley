package com.groeps33.valley.renderer;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
public class Message {


    public enum Type { SERVER(Color.GREEN), FRIENDLY_FIRE(Color.RED), PLAYER(Color.WHITE);

        private Color color;


        Type(Color color) {
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    private static final int DISPLAY_TIME = 2000;
    private static final int FADE_OUT_TIME = 750;

    private final String message;
    private final Type type;
    private final long startTime;
    private long startFadeTime;

    public Message(String message, Type type) {
        this.message = message;
        this.type = type;
        this.startTime = System.currentTimeMillis();
    }


    public Type getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public float getAlpha() {
        long timePassed = System.currentTimeMillis() - startTime;
        if (timePassed < DISPLAY_TIME) {
            startFadeTime = System.currentTimeMillis();
            return 1f;
        }

        return 0f; //todo fade out
    }

    public boolean isValid() {
        return  System.currentTimeMillis() - startTime < DISPLAY_TIME + FADE_OUT_TIME;
    }
}
