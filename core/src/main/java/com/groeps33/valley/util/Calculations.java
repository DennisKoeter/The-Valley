package com.groeps33.valley.util;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Bram on 10-4-2016.
 */
public class Calculations {
    public static double distance(float x1, float y1, float x2, float y2) {
        final float deltaX = x2 - x1;
        final float deltaY = y2 - y1;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public static double distance(Vector2 v1, Vector2 v2) {
        return distance(v1.x, v1.y, v2.x, v2.y);
    }
}
