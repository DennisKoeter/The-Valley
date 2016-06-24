package com.groeps33.valley.net;

import com.groeps33.valley.Constants;
import com.groeps33.valley.entity.Monster;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
class Wave {
    private final int number;
    private final long startTime;
    private boolean finished;
    private final List<Monster> monsterList;
    private int monstersSpawned = 0;
    private int totalToBeSpawned;
    private long lastSpawn;

    public Wave(int number, long startTime) {
        this.number = number;
        this.startTime = startTime;
        monsterList = new CopyOnWriteArrayList<>();
        totalToBeSpawned = 10 + number * 2;
    }

    public int getNumber() {
        return number;
    }

    public long getStartTime() {
        return startTime;
    }

    public boolean isFinished() {
        return finished;
    }

    public List<Monster> getMonsterList() {
        return monsterList;
    }

    public void checkSpawn() {
        if (monstersSpawned < totalToBeSpawned &&  System.currentTimeMillis() - lastSpawn > 1000) {
            monsterList.add(new Monster(monstersSpawned++, Constants.MONSTER_SPAWN_LOCATION_X, Constants.MONSTER_SPAWN_LOCATION_Y, Constants.MONSTER_NAME, Constants.MONSTER_MAXHP, Constants.MONSTER_DEFENCE, Constants.MONSTER_ATTACK_DAMAGE, Constants.MONSTER_MOVESPEED));
            lastSpawn = System.currentTimeMillis();
        }
    }
}
