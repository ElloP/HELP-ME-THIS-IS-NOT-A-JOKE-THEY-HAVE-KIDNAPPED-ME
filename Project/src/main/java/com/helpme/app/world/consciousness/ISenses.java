package com.helpme.app.world.consciousness;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.target.ITarget;

import java.util.List;

/**
 * Created by kopa on 2017-05-04.
 */
public interface ISenses {
    boolean isMonsterBlockedByEdge(IReadBody monster, Vector2f direction);
    boolean isMovementAllowed(IReadBody monster, Vector2f direction);
    boolean isTileOccupied(Vector2f position);
    boolean isTileValid(Vector2f position);
    boolean isDistanceFrom(IReadBody monster, Vector2f destination, int longestDistance);
    Tuple3<List<Vector2f>, Vector2f, Integer> getShortestPath(Vector2f from, Vector2f to);
    Maybe<IReadBody> readPlayer();
    Maybe<IReadBody> readMonster(Vector2f position);
    Maybe<ITarget> getTarget(IBody monster, Vector2f direction);
    Maybe<IReadBody> readFacing(IReadBody monster);
}
