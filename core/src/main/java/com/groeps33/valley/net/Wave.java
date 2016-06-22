package com.groeps33.valley.net;

import com.badlogic.gdx.math.Vector2;
import com.groeps33.valley.entity.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
class Wave {
    private final static Vector2 MONSTER_SPAWN = new Vector2(309, 1355);
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
            monsterList.add(new Monster(i, MONSTER_SPAWN.x, MONSTER_SPAWN.y, "Monster", 100, -1, 10, 150));
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
