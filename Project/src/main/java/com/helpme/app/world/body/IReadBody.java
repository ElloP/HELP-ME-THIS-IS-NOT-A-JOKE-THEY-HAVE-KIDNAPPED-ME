package com.helpme.app.world.body;

import com.helpme.app.utils.interfaces.IObservable;
import com.helpme.app.world.body.inventory.IReadInventory;
import com.helpme.app.world.item.effect.ITarget;
import com.helpme.app.world.tile.edge.IEdge;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.utils.Vector2f;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IReadBody extends ITarget, IObservable {
    Vector2f readPosition();
    Vector2f readDirection();
    Vector2f readHitpoints();
    float readMaxHp();
    float readCurrentHp();
    Vector2f readStartingPosition();
    Tuple2<String,String[]> getResponse(int i); //NOTE (Jacob) : Not sure if this is something that should be in ReadMonster
    Tuple2<String,String[]> getDialogue();
    boolean isTraversable(IEdge edge);
    IReadInventory readInventory();
}
