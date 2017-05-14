package com.helpme.app.world.character;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.inventory.IInventory;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IBody extends IReadBody {
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
    IInventory getInventory();
    void setItems(IItem[] items);
    Body clone();
    void dropAllItems();
    void kill();
}
