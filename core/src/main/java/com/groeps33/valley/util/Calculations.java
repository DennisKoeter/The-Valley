package com.groeps33.valley.util;

/**
 * Created by Bram on 10-4-2016.
 */
public class Calculations {
    public static double distance(int x1, int y1, int x2, int y2) {
        final int deltaX = x2 - x1;
        final int deltaY = y2 - y1;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
