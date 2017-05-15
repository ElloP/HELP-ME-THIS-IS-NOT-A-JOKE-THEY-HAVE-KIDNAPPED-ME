package com.helpme.app.world.tile.edge;

import com.helpme.app.world.item.IItem;
import com.helpme.app.world.item.IKeyFactory;
import com.sun.javafx.geom.Edge;

/**
 * Created by kopa on 2017-05-15.
 */
public final class EdgeFactory {
    private EdgeFactory(){

    }

    public static IEdge createDoor(boolean locked, IItem key){
        return new Door(locked, key == null ? IKeyFactory.skeletonKey() : key);
    }

    public static IEdge createOpening(){
        return new Opening();
    }

    public static IEdge createWall(){
        return new Wall();
    }
}
