package com.groeps33.valley.shop;

import com.groeps33.valley.entity.Character;

/**
 * Created by Dennis on 06/04/16.
 */
public class Statboost {
    private String name;
    private Stats stat;
    private int boost;
    private int cost;
    private int duration;

    public Statboost(String name, Stats stat, int cost, int boost, int duration) {
        this.name = name;
        this.stat = stat;
        this.cost = cost;
        this.boost = boost;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public Stats getStat() {
        return stat;
    }

    public int getBoost() {
        return boost;
    }

    public int getDuration(){
        return duration;
    }

    public int getCost(){
        return cost;
    }

    /**
     * reduces the duration of the statboost
     * @param amount the amount by which the duration will be reduced
     */
    public void reduceDuration(int amount){
        this.duration -= amount;
    }
}
