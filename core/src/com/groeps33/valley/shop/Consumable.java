package com.groeps33.valley.shop;

/**
 * Created by Robin on 6-4-2016.
 */
public class Consumable extends IngameShop {
    private String name;
    private Stat  stat;
    private int boost;
    private int cost;

    public Consumable(String name, Stat stat, int boost, int cost)
    {
        this.name = name;
        this.stat = stat;
        this.boost = boost;
        this.cost = cost;
    }

    public enum Stat
    {

    }

    public void Consume(Character c)
    {

    }
}
