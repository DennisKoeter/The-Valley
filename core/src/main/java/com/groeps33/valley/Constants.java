package com.groeps33.valley;

/**
 * @author Edwin
 */

public class Constants {

    /**
     * Net
     */
    public static final int SERVER_PORT = 8009;
    //    public static final String HOST_IP = "169.254.195.1";
    public static final String HOST_IP = "127.0.0.1";
    public static final long TICK_INTERVAL = 50;
    public static final long SYNC_ALL_PLAYERS_INTERVAL = 500;

    /**
     * Monsters
     */
    public static final String MONSTER_NAME = "Monster";
    public static final int MONSTER_SPAWN_LOCATION_X = 785;
    public static final int MONSTER_SPAWN_LOCATION_Y = 1835;
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

    /**
     * Sound
     */
    private static String PREFIX = "sound/";
    public static final String SOUND_ON_HIT = PREFIX + "on_hit.mp3";
    public static final String SOUND_ON_CHARACTER_DEATH = PREFIX + "player_death.mp3";
    public static final String SOUND_ON_CHARACTER_RESPAWN = PREFIX + "player_respawn.mp3";
    public static final String SOUND_SHOOT_ARCHER = PREFIX + "shoot_archer.mp3";
    public static final String SOUND_SHOOT_MAGE = PREFIX + "shoot_mage.mp3";
    public static final String SOUND_SHOOT_WARRIOR = PREFIX + "shoot_warrior.mp3";
    public static final String SOUND_SPECIAL_ARCHER = PREFIX + "boost_armor.mp3";
    public static final String SOUND_SPECIAL_MAGE = PREFIX + "boost_hp.mp3";
    public static final String SOUND_SPECIAL_WARRIOR = PREFIX + "boost_movementspeed.mp3";
}
