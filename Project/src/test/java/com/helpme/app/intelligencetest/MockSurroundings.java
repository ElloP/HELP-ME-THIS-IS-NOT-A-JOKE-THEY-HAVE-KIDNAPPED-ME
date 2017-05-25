package com.helpme.app.intelligencetest;

import com.helpme.app.game.model.body.IBody;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IReadSurroundings;
import com.helpme.app.game.model.item.effect.ITarget;
import com.helpme.app.game.model.tile.ITile;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple3;

import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-22.
 */
public class MockSurroundings implements IReadSurroundings {
    IReadBody mockBody = null;

    @Override
    public boolean isDirectionBlocked(IReadBody body, Vector2f direction) {
        return false;
    }

    @Override
    public boolean isMovementAllowed(IReadBody body, Vector2f direction) {
        return false;
    }

    @Override
    public boolean isTileOccupied(Vector2f position) {
        return false;
    }

    @Override
    public boolean isTileValid(Vector2f position) {
        return false;
    }

    @Override
    public boolean isWithinRange(Vector2f position, Vector2f destination, int range) {
        return false;
    }

    @Override
    public Tuple3<List<Vector2f>, Vector2f, Integer> getPath(Vector2f from, Vector2f to) {
        return null;
    }

    @Override
    public Maybe<IReadBody> readPlayer() {
        return null;
    }

    @Override
    public Maybe<IReadBody> readBody(Vector2f position) {
        return Maybe.wrap(mockBody != null && mockBody.readPosition().equals(position) ? mockBody : null);
    }

    @Override
    public Maybe<ITarget> getTarget(IBody body, Vector2f direction) {
        return null;
    }

    @Override
    public Maybe<IReadBody> readFacing(IReadBody body) {
        return null;
    }

    @Override
    public List<IReadBody> readBodies() {
        return null;
    }

    @Override
    public Vector2f readStartingPoint() {
        return null;
    }

    @Override
    public Map<Vector2f, ITile> getTiles() {
        return null;
    }
}
