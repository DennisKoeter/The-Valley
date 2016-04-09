package com.groeps33.valley.shop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.groeps33.valley.entity.Character;

/**
 * Created by Robin on 6-4-2016.
 */
public class IngameShop {
    private List<Consumable> consumables;

    public IngameShop(){
        this.consumables = new ArrayList<Consumable>();
    }

    /**
     * adds a consumable to the shop, ready to be sold!
     * @param consumable the consumable to be added
     */
    public void addConsumable(Consumable consumable){
        this.consumables.add(consumable);
    }

    /**
     * returns a list containing all the consumables the shop has to offer.
     * @return a list containing all the consumables in the shop.
     */
    public List<Consumable> getConsumables(){
        return this.consumables;
    }

    /**
     * finds a specific consumable in the shop, based on the name.
     * @param consumableName the name of the consumable to be found
     * @return the resulting Consumable object
     */
    public Consumable findConsumable(String consumableName){
        for(Consumable consumable : this.consumables){
            if (consumable.getName().equals(consumableName)){
                return consumable;
            }
        }
        return null;
    }

    /**
     * purchases a consumable for a specific character
     * @param consumableName the name of the consumable to be purchased
     * @param customer the character making the purchase
     * @return the purchased consumable, in case you want to display it or something..
     */
    public Consumable buyConsumable(String consumableName, Character customer){
        Consumable consumable = this.findConsumable(consumableName);
        if(consumable == null) return null;
        if(customer.getGold() < consumable.getCost()) return null;
        customer.reduceGold(consumable.getCost());
        customer.setConsumable(consumable);
        return consumable;
    }
}
