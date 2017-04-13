package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.ITarget;
import com.helpme.app.item.IItem;
import com.helpme.app.utils.Vector2f;

import java.util.List;
import java.util.Stack;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface ILevel {
    boolean isEdgeBlocked(IMonster monster, Vector2f position, Vector2f direction);
    boolean isTileOccupied(Vector2f position);
    boolean isTileValid(Vector2f position);
    IItem[] removeTileItems(Vector2f position);
    IItem removeTileItem(Vector2f position, int index);
    void addTileItem(Vector2f position, IItem item);
    void addTileItems(Vector2f position, IItem[] items);
    IMonster getMonster(Vector2f position);
    IMonster getPlayer();
    ITarget getTarget(Vector2f position, Vector2f direction);
}
