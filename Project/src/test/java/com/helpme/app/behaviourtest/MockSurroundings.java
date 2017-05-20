package com.helpme.app.behaviourtest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.IReadSurroundings;
import com.helpme.app.world.item.effect.ITarget;
import com.helpme.app.world.tile.ITile;

import java.util.List;
import java.util.Map;

/**
 * Created by kopa on 2017-05-20.
 */
public class MockSurroundings implements IReadSurroundings {
    Vector2f pathNextPosition = Vector2f.zero;
    int pathCost = 3;
    boolean facing;
    IReadBody player;

    public MockSurroundings(IReadBody player){
        this.player = player;
    }

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
    public boolean isDistanceFrom(IReadBody body, Vector2f destination, int longestDistance) {
        return false;
    }

    @Override
    public Tuple3<List<Vector2f>, Vector2f, Integer> getShortestPath(Vector2f from, Vector2f to) {
        return new Tuple3<>(null, pathNextPosition, pathCost);
    }

    @Override
    public Maybe<IReadBody> readPlayer() {
        return Maybe.wrap(player);
    }

    @Override
    public Maybe<IReadBody> readBody(Vector2f position) {
        return null;
    }

    @Override
    public Maybe<ITarget> getTarget(IBody body, Vector2f direction) {
        return null;
    }

    @Override
    public Maybe<IReadBody> readFacing(IReadBody body) {
        return Maybe.wrap(facing ? new MockBody() : null);
    }

    @Override
    public IReadBody[] readBodies() {
        return new IReadBody[0];
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
