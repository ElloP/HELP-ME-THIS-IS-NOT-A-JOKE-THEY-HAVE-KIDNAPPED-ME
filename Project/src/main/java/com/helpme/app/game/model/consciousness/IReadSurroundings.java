package com.helpme.app.game.model.consciousness;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.item.effect.ITarget;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple3;

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
    boolean isWithinRange(Vector2f position, Vector2f destination, int range);
    Tuple3<List<Vector2f>, Vector2f, Integer> getPath(Vector2f from, Vector2f to);
    Maybe<IReadBody> readPlayer();
    Maybe<IReadBody> readBody(Vector2f position);
    Maybe<ITarget> getTarget(IBody body, Vector2f direction);
    Maybe<IReadBody> readFacing(IReadBody body);
    List<IReadBody> readBodies();
    Vector2f readStartingPoint();
    Map<Vector2f, ITile> getTiles();
}
