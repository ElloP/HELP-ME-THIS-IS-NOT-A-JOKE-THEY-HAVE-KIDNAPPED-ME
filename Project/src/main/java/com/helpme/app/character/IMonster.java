package com.helpme.app.character;

import com.helpme.app.character.inventory.IInventory;
import com.helpme.app.item.IItem;
import com.helpme.app.tile.edge.IEdge;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface IMonster extends ITarget, IReadMonster {
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
    Vector2f targetTile();
    boolean pickupItem(IItem item);
    IItem dropItem(int index);
    IInventory getInventory();
    void setItems(IItem[] items);
    Monster clone();
}
