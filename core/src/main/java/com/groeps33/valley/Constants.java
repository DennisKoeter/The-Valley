package com.groeps33.valley;

/**
 * @author Edwin
 */

public class Constants {

    /**
     * Net
     */
    public static final int SERVER_PORT = 8009;
    public static final long TICK_INTERVAL = 100;
    public static final long SYNC_ALL_PLAYERS_INTERVAL = 500;

    /**
     * Monsters
     */
    public static final String MONSTER_NAME = "Monster";
    public static final int MONSTER_SPAWN_LOCATION_X = 309;
    public static final int MONSTER_SPAWN_LOCATION_Y = 1355;
    public static final int MONSTER_MAXHP = 100;
    public static final int MONSTER_DEFENCE = -1;
    public static final int MONSTER_ATTACK_DAMAGE = 10;
    public static final int MONSTER_MOVESPEED = 150;

    /**
     * Misc
     */
    public static final int HEALTH_POT_COOLDOWN = 5; // in seconds
    public static int HEALTH_POT_LOCATION_X = 300;
    public static int HEALTH_POT_LOCATION_Y = 300;
    public static int HEALTH_POT_AMOUNT_OF_HEAL = 25;
}
