package com.groeps33.valley.shop;

import com.groeps33.valley.entity.Character;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dennis on 06/04/16.
 */
public class DoubloonShop {
    List<Statboost> statboosts;

    public DoubloonShop(){
        this.statboosts = new ArrayList<Statboost>();
    }

    public void addStatboost(Statboost statboost){
        this.statboosts.add(statboost);
    }

    public Iterator<Statboost> getStatboosts(){
        return this.statboosts.iterator();
    }

    public Statboost findStatboost(String statboostName){
        for(Statboost statboost : this.statboosts){
            if (statboost.getName().equals(statboostName)){
                return statboost;
            }
        }
        return null;
    }

    public Statboost buyStatboost(String statboostName, Character customer){
        Statboost statboost = this.findStatboost(statboostName);
        if(statboost == null) return null;
        customer.boostStat(statboost);
        return statboost;
    }
}
