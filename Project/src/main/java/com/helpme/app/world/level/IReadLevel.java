package com.helpme.app.world.level;
import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.character.ITarget;
import com.helpme.app.utils.Vector2f;

import java.util.List;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IReadLevel {
    boolean isMonsterBlockedByEdge(IReadMonster monster, Vector2f direction);
    boolean isTileOccupied(Vector2f position);
    boolean isTileValid(Vector2f position);
    boolean isDistanceFrom(IReadMonster monster, Vector2f destination, int longestDistance);
    Tuple3<List<Vector2f>, Vector2f, Integer> getShortestPath(Vector2f from, Vector2f to);
    Maybe<IReadMonster> readPlayer();
    Maybe<IReadMonster> readMonster(Vector2f position);
    Maybe<ITarget> getTarget(IMonster monster, Vector2f direction);
}
