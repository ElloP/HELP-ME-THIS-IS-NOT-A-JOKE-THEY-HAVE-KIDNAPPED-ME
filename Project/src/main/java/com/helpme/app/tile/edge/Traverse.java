package com.helpme.app.tile.edge;

import com.helpme.app.character.IInventory;
import com.helpme.app.character.Monster;
import com.helpme.app.item.Item;

/**
 * Created by kopa on 2017-04-08.
 */
public class Traverse implements IEdgeVisitor {
    private final IInventory inventory;

    public Traverse(IInventory inventory){
        this.inventory = inventory;
    }

    @Override
    public boolean visit(Door door) {
        if(door.isLocked()){
            if(inventory.hasItem(door.getKey())){
                door.unlock();
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean visit(Wall wall) {
        return false;
    }

    @Override
    public boolean visit(Opening opening) {
        return true;
    }
}
