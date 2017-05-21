package com.helpme.app.world.body;

import com.helpme.app.utils.interfaces.ICloneable;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.body.inventory.IInventory;
import com.helpme.app.world.item.effect.ITarget;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.Vector2f;

import java.util.List;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IBody extends IReadBody, ICloneable<IBody> {
    void rotateRight();
    void rotateLeft();
    void moveForward();
    void moveRight();
    void moveBackward();
    void moveLeft();
    void setPosition(Vector2f position);
    void changeActiveItem(int itemIndex);
    void attack(ITarget target);
    void selfie();
    boolean pickupItem(IItem item);
    Maybe<IItem> dropItem(int index);
    List<Maybe<IItem>> dropAllItems();
    IInventory getInventory();
    void setItems(IItem[] items);

    void kill();
}
