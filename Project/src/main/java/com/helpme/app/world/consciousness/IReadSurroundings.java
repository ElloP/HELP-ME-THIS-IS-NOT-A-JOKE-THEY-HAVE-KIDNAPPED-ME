package com.helpme.app.world.consciousness;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.target.ITarget;
import com.helpme.app.world.tile.ITile;

import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-04.
 */
public interface IReadSurroundings {
    boolean isBlockedByEdge(IReadBody body, Vector2f direction);
    boolean isMovementAllowed(IReadBody body, Vector2f direction);
    boolean isTileOccupied(Vector2f position);
    boolean isTileValid(Vector2f position);
    boolean isDistanceFrom(IReadBody body, Vector2f destination, int longestDistance);
    Tuple3<List<Vector2f>, Vector2f, Integer> getShortestPath(Vector2f from, Vector2f to);
    Maybe<IReadBody> readPlayer();
    Maybe<IReadBody> readBody(Vector2f position);
    Maybe<ITarget> getTarget(IBody body, Vector2f direction);
    Maybe<IReadBody> readFacing(IReadBody body);
    IReadBody[] readMonsters();
    Vector2f readStartingPoint();
    Map<Vector2f, ITile> getTiles();
}
