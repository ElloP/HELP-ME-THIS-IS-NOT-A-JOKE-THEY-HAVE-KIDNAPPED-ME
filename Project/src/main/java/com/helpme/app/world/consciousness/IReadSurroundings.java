package com.helpme.app.world.consciousness;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.item.effect.ITarget;
import com.helpme.app.world.tile.ITile;

import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-04.
 */
public interface IReadSurroundings {
    boolean isDirectionBlocked(IReadBody body, Vector2f direction);
    boolean isMovementAllowed(IReadBody body, Vector2f direction);
    boolean isTileOccupied(Vector2f position);
    boolean isTileValid(Vector2f position);
    boolean isDistanceFrom(IReadBody body, Vector2f destination, int longestDistance);
    Tuple3<List<Vector2f>, Vector2f, Integer> getShortestPath(Vector2f from, Vector2f to);
    Maybe<IReadBody> readPlayer();
    Maybe<IReadBody> readBody(Vector2f position);
    Maybe<ITarget> getTarget(IBody body, Vector2f direction);
    Maybe<IReadBody> readFacing(IReadBody body);
    IReadBody[] readBodies();
    Vector2f readStartingPoint();
    Map<Vector2f, ITile> getTiles();
}
