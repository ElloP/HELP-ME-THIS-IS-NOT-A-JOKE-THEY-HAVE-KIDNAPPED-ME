package com.helpme.app.world.consciousness.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.consciousness.ISurroundings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopa on 2017-04-14.
 */
public abstract class Intelligence implements IBehaviour {

    private Intelligence() {

    }

    public static boolean isMonsterNextTo(IReadBody body, IReadBody potentialNeighbour, ISurroundings surroundings) {
        for (Maybe<IReadBody> maybeNeighbour : getMonsterNeighbours(body, surroundings)) {
            if(maybeNeighbour.check(n -> potentialNeighbour.equals(n))){
                return true;
            }
        }
        return false;
    }

    public static List<Maybe<IReadBody>> getMonsterNeighbours(IReadBody body, ISurroundings surroundings) {
        return new ArrayList(4) {
            {
                add(getMonsterFrontNeighbour(body, surroundings));
                add(getMonsterRightNeighbour(body, surroundings));
                add(getMonsterBackNeighbour(body, surroundings));
                add(getMonsterLeftNeighbour(body, surroundings));
            }
        };
    }

    public static Maybe<IReadBody> getMonsterFrontNeighbour(IReadBody body, ISurroundings surroundings) {
        Vector2f direction = body.readDirection().forward();
        Vector2f frontPosition = Vector2f.add(body.readPosition(), direction);
        return surroundings.isDirectionBlocked(body, direction) ? new Nothing() : surroundings.readBody(frontPosition);

    }

    public static Maybe<IReadBody> getMonsterRightNeighbour(IReadBody body, ISurroundings surroundings) {
        Vector2f direction = body.readDirection().forward();
        Vector2f rightPosition = Vector2f.add(body.readPosition(), direction.right());
        return surroundings.isDirectionBlocked(body, direction) ? new Nothing() : surroundings.readBody(rightPosition);
    }

    public static Maybe<IReadBody> getMonsterBackNeighbour(IReadBody body, ISurroundings surroundings) {
        Vector2f direction = body.readDirection().forward();
        Vector2f backPosition = Vector2f.add(body.readPosition(), direction.backward());
        return surroundings.isDirectionBlocked(body, direction) ? new Nothing() : surroundings.readBody(backPosition);
    }

    public static Maybe<IReadBody> getMonsterLeftNeighbour(IReadBody body, ISurroundings surroundings) {
        Vector2f direction = body.readDirection().forward();
        Vector2f leftPosition = Vector2f.add(body.readPosition(), direction.left());
        return surroundings.isDirectionBlocked(body, direction) ? new Nothing() : surroundings.readBody(leftPosition);
    }

    public static boolean isMonsterFacing(IReadBody body, Vector2f other){
        return Vector2f.equals(Vector2f.add(body.readPosition(), body.readDirection()), other);
    }

    public static boolean isLeftOf(IReadBody body, Vector2f other){
        Vector2f right = body.readDirection().right();

        return Vector2f.equals(Vector2f.add(body.readPosition(), right), other);
    }

    public static Either moveOrRotateAction(IReadBody body, Vector2f nextPos) {
        if (Intelligence.isMonsterFacing(body, nextPos)){
            return Action.moveForwardAction();
        } else if (Intelligence.isLeftOf(body, nextPos)){
            return Action.rotateRight();
        } else {
            return Action.rotateLeft();
        }
    }
}
