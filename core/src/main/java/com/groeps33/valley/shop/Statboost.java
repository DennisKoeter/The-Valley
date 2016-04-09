package com.groeps33.valley.shop;

/**
 * Created by Dennis on 06/04/16.
 */
public class Statboost {
    private String name;
    private Stat stat;
    private int boost;
    private int cost;
    private int duration;

    public Statboost(String name, Stat stat, int cost, int boost, int duration) {
        this.name = name;
        this.stat = stat;
        this.cost = cost;
        this.boost = boost;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public Stat getStat() {
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
     * @param amount the amount by which the duration will be reduced, can be negative for increase
     */
    public void reduceDuration(int amount){
        this.duration -= amount;
    }
}
