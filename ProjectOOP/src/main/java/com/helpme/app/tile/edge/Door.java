package com.helpme.app.tile.edge;

import com.helpme.app.item.Item;

/**
 * Created by Jacob on 2017-03-30.
 */
public class Door implements Edge{
    private boolean locked;
    private Item key;

    public Door(boolean locked, Item key){
        this.locked = locked;
        this.key = key == null ? new Item("Skeleton Key") : key.clone();
    }

    @Override
    public boolean unlock(Item[] potentialKeys){
        for(Item key : potentialKeys){
            if(this.key.equals(key)){
                this.locked = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean traverse() {
        return !locked;
    }
}
