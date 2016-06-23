package com.groeps33.valley.net;

import com.groeps33.valley.Constants;
import com.groeps33.valley.entity.Monster;

import java.util.ArrayList;
import java.util.List;

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

    public Wave(int number, long startTime) {
        this.number = number;
        this.startTime = startTime;
        monsterList = new ArrayList<>();
        spawnMonsters();
    }

    private void spawnMonsters() {
        for (int i = 0; i < 10 + number * 2; i++) {
            monsterList.add(new Monster(i, Constants.MONSTER_SPAWN_LOCATION_X, Constants.MONSTER_SPAWN_LOCATION_Y, Constants.MONSTER_NAME, Constants.MONSTER_MAXHP, Constants.MONSTER_DEFENCE, Constants.MONSTER_ATTACK_DAMAGE, Constants.MONSTER_MOVESPEED));
        }
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
}
