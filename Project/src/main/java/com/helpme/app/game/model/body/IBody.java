package com.helpme.app.game.model.body;

import com.helpme.app.game.model.body.inventory.IInventory;
import com.helpme.app.game.model.item.IItem;
import com.helpme.app.game.model.item.effect.ITarget;
import com.helpme.app.game.model.tile.edge.IEdge;
import com.helpme.app.utils.interfaces.ICopyable;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;

import java.util.List;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IBody extends IReadBody, ICopyable<IBody> {
    void rotateRight();
    void rotateLeft();
    void moveForward();
    void moveRight();
    void moveBackward();
    void moveLeft();
    void setPosition(Vector2f position);
    void setActiveItem(int itemIndex);
    void attack(ITarget target);
    void selfie();
    void setItems(IItem[] items);
    void kill();
    boolean pickupItem(IItem item);
    boolean unlock(IEdge edge);
    Maybe<IItem> dropItem(int index);
    List<Maybe<IItem>> dropAllItems();
    IInventory getInventory();
}
