package com.helpme.app.world.character;

import com.helpme.app.world.character.inventory.IReadInventory;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.Vector2f;

import java.util.Observable;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IReadBody extends ITarget {
    Vector2f readPosition();
    Vector2f readDirection();
    Vector2f readHitpoints();
    Vector2f readStartingPosition();
    Tuple2<String,String[]> getResponse(int i); //NOTE (Jacob) : Not sure if this is something that should be in ReadMonster
    Tuple2<String,String[]> getDialogue();
    boolean isTraversable(IEdge edge);
    IReadInventory readInventory();
}
