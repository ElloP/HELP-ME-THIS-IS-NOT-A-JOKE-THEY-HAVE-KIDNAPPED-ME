package com.helpme.app.world.level;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.character.ITarget;
import com.helpme.app.utils.Vector2f;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IReadLevel {
    boolean isMonsterBlockedByEdge(IReadMonster monster, Vector2f direction);
    boolean isTileOccupied(Vector2f position);
    boolean isTileValid(Vector2f position);
    Maybe<IReadMonster> getPlayer();
    boolean isDistanceFrom(IReadMonster monster, Vector2f destination, int longestDistance);
    Maybe<IReadMonster> getMonster(Vector2f position);
    Maybe<ITarget> getTarget(IMonster monster, Vector2f direction);
}
