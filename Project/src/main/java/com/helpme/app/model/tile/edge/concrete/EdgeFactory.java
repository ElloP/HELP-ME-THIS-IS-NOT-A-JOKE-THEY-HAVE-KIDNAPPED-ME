package com.helpme.app.model.tile.edge.concrete;

import com.helpme.app.model.item.IItem;
import com.helpme.app.model.item.concrete.ItemFactory;
import com.helpme.app.model.tile.edge.IEdge;

/**
 * Created by kopa on 2017-05-15.
 */
public final class EdgeFactory {
    private EdgeFactory(){

    }

    public static IEdge createDoor(boolean locked, IItem key){
        return new Door(locked, key == null ? ItemFactory.createKey("Skeleton Key") : key);
    }

    public static IEdge createOpening(){
        return new Opening();
    }

    public static IEdge createWall(){
        return new Wall();
    }
}
