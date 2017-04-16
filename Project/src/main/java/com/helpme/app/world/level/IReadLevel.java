package com.helpme.app.world.level;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.IReadMonster;
import com.helpme.app.character.ITarget;
import com.helpme.app.utils.Vector2f;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IReadLevel {
    boolean isMonsterBlockedByEdge(IReadMonster monster, Vector2f direction);
    boolean isTileOccupied(Vector2f position);
    boolean isTileValid(Vector2f position);
    IReadMonster getPlayer();
    boolean isDistanceFrom(IReadMonster monster, Vector2f destination, int longestDistance);
    IReadMonster getMonster(Vector2f position);
    ITarget getTarget(IMonster monster, Vector2f direction);
}
