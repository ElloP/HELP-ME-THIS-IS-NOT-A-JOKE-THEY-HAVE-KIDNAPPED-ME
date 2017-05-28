package com.helpme.app.game.model.body;

import com.helpme.app.game.model.body.inventory.IReadInventory;
import com.helpme.app.game.model.item.effect.ITarget;
import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.utils.interfaces.IObservable;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.tuple.Tuple2;

/**
 * Created by kopa on 2017-04-14.
 *
 * Interface for reading the values of a body
 *
 */
public interface IReadBody extends ITarget, IObservable {
    Vector2f readPosition();
    Vector2f readDirection();
    Vector2f readHitpoints();
    float readMaxHitpoints();
    float readCurrentHitpoints();
    Vector2f readStartingPosition();
    Tuple2<String,String[]> getResponse(int i);
    Tuple2<String,String[]> getDialogue();
    boolean traverse(IEdge edge);
    IReadInventory readInventory();
}
