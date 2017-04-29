package com.helpme.app.world.level;

import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.item.IItem;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface ILevel extends IReadLevel {
    IItem[] removeTileItems(Vector2f position);
    IItem removeTileItem(Vector2f position, int index);
    void addMonster(IMonster monster);
    void addTileItem(Vector2f position, IItem item);
    void addTileItems(Vector2f position, IItem[] items);
    void setPlayer(IMonster player);
    void updateDeadMonster(Vector2f position);
    String toString();
}
