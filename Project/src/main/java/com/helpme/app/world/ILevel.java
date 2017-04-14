package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.IReadMonster;
import com.helpme.app.character.ITarget;
import com.helpme.app.item.IItem;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jacob on 2017-04-08.
 */
public interface ILevel {
    boolean isMonsterBlockedByEdge(IMonster monster, Vector2f direction);
    boolean isTileOccupied(Vector2f position);
    boolean isTileValid(Vector2f position);
    IItem[] removeTileItems(Vector2f position);
    IItem removeTileItem(Vector2f position, int index);
    void addMonster(IMonster monster);
    void addTileItem(Vector2f position, IItem item);
    void addTileItems(Vector2f position, IItem[] items);
    void setPlayer(IMonster player);
    IMonster getMonster(Vector2f position);
    IMonster getPlayer();
    boolean isDistanceFrom(IMonster monster, Vector2f destination, int longestDistance);
    ITarget getTarget(Vector2f position, Vector2f direction);
}
