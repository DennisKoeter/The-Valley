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

    /**
     * adds a statboost to the shop, ready to be sold!
     * @param statboost the statboost that will be added to the shop
     */
    public void addStatboost(Statboost statboost){
        this.statboosts.add(statboost);
    }

    /**
     * returns an iterator containing all the statboosts the shop has to offer.
     * @return an iterator containing all the statboosts in the shop.
     */
    public Iterator<Statboost> getStatboosts(){
        return this.statboosts.iterator();
    }

    /**
     * finds a specific stat boost in the shop, based on the name.
     * @param statboostName the name of the statboost to be found
     * @return the resulting Statboost object
     */
    public Statboost findStatboost(String statboostName){
        for(Statboost statboost : this.statboosts){
            if (statboost.getName().equals(statboostName)){
                return statboost;
            }
        }
        return null;
    }

    /**
     * purchases a statboost for a specific character
     * @param statboostName the name of the statboost to be purchased
     * @param customer the character making the purchase
     * @return the purchased statboost, in case you want to display it or something..
     */
    public Statboost buyStatboost(String statboostName, Character customer){
        Statboost statboost = this.findStatboost(statboostName);
        if(statboost == null) return null;
        if(customer.getGold() < statboost.getCost()) return null;
        customer.reduceGold(statboost.getCost());
        customer.addStatboost(statboost);
        return statboost;
    }
}
