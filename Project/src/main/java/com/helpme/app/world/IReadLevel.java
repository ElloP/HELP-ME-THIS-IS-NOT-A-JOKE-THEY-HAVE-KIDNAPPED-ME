package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.ITarget;
import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.Vector2f;

import java.util.List;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IReadLevel {
    boolean isMonsterBlockedByEdge(IMonster monster, Vector2f direction);
    boolean isTileOccupied(Vector2f position);
    boolean isTileValid(Vector2f position);
    IMonster getPlayer();
    boolean isDistanceFrom(IMonster monster, Vector2f destination, int longestDistance);
    IMonster getMonster(Vector2f position);
    ITarget getTarget(IMonster monster, Vector2f direction);
    Tuple3<List<Vector2f>, Vector2f, Integer> getShortestPath(Vector2f from, Vector2f to);
}
