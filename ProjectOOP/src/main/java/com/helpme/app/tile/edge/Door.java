package com.helpme.app.tile.edge;

import com.helpme.app.item.Item;

/**
 * Created by kopa on 2017-03-30.
 */
public class Door implements Edge {
    private boolean unlocked;
    private Item key;

    public boolean traverse() {
        return unlocked;
    }

    public boolean unlock(Item item) {
        if(key.equals(item)){
            System.out.println("Unlocked door");
            unlocked = true;
            return true;
        }
        System.out.println("Door is locked and you don't have the key");
        return false;
    }
}
