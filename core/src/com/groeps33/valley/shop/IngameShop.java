package com.groeps33.valley.shop;

import java.util.ArrayList;
import java.util.List;
import com.groeps33.valley.entity.Character;

/**
 * Created by Robin on 6-4-2016.
 */
public class IngameShop {
    List<Consumable> consumables;

    public IngameShop(){
        this.consumables = new ArrayList<Consumable>();
    }

    public void addConsumable(Consumable consumable){
        this.consumables.add(consumable);
    }

    public Consumable findConsumable(String consumableName){
        for(Consumable consumable : this.consumables){
            if (consumable.getName().equals(consumableName)){
                return consumable;
            }
        }
        return null;
    }

    public Consumable buyConsumable(String consumableName, Character customer){
        Consumable consumable = this.findConsumable(consumableName);
        if(consumable == null) return null;
        customer.setConsumable(consumable);
    }
}
