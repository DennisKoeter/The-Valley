package com.groeps33.valley.items;

/**
 * @author Edwin
 *         <p>
 *         An item spawn which can be found on the game map.
 */

public abstract class ItemSpawn {
    // location on the map
    private int x;
    private int y;

    // in seconds
    private int cooldown;

    // time in millis when the item was taken from the ground
    private long disabledSince;

    public long getDisabledSince() {
        return disabledSince;
    }

    /**
     * Set disabled when someone took the item off the ground
     *
     * @param bool true to say someone took the item
     */
    public void setTaken(boolean bool) {
        disabledSince = System.currentTimeMillis();
    }

    /**
     * If current time minus disabled since is longer or equal to cooldown,
     * the item can get off cooldown and spawn. So in that case return true,
     * if the item is still on cooldown return false.
     */
    public boolean isReadyToSpawn() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - disabledSince >= (cooldown * 1000)) {
            return true;
        } else {
            return false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void spawn() {
        // todo: actually spawn the thing
    }

    public ItemSpawn(int x, int y, int cooldown, long disabledSince) {
        this.x = x;
        this.y = y;
        this.cooldown = cooldown;
        this.disabledSince = disabledSince;
    }
}
