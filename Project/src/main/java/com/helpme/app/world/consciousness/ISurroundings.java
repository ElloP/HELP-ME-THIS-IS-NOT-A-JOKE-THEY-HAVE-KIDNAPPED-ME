package com.helpme.app.world.consciousness;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.item.IItem;

/**
 * Created by kopa on 2017-04-14.
 */
public interface ISurroundings extends ISenses {
    void addTileItem(Vector2f position, IItem item);
    void addTileItems(Vector2f position, IItem[] items);
    IItem[] removeTileItems(Vector2f position);
    IItem removeTileItem(Vector2f position, int index);
    void updateDeadMonster(Vector2f position);

}
