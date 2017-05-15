package com.helpme.app.world.tile.edge;

import com.helpme.app.world.item.IItem;
import com.sun.javafx.geom.Edge;

/**
 * Created by kopa on 2017-05-15.
 */
public final class EdgeFactory {
    private EdgeFactory(){

    }

    public static IEdge createDoor(boolean locked, IItem key){
        return new Door(locked, key);
    }

    public static IEdge createOpening(){
        return new Opening();
    }

    public static IEdge createWall(){
        return new Wall();
    }
}
