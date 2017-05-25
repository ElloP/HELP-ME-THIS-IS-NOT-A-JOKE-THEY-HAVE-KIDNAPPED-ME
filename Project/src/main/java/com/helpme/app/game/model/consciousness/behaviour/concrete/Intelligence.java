package com.helpme.app.game.model.consciousness.behaviour.concrete;

import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IReadSurroundings;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopa on 2017-04-14.
 */
public abstract class Intelligence implements IBehaviour {

    private Intelligence() {

    }

    public static boolean isNextTo(IReadBody body, IReadBody potentialNeighbour, IReadSurroundings surroundings) {
        for (Maybe<IReadBody> maybeNeighbour : getNeighbours(body, surroundings)) {
            if(maybeNeighbour.check(n -> potentialNeighbour.equals(n))){
                return true;
            }
        }
        return false;
    }

    public static List<Maybe<IReadBody>> getNeighbours(IReadBody body, IReadSurroundings surroundings) {
        List<Maybe<IReadBody>> neighbours = new ArrayList<>(4);
        neighbours.add(getFrontNeighbour(body, surroundings));
        neighbours.add(getRightNeighbour(body, surroundings));
        neighbours.add(getBackNeighbour(body, surroundings));
        neighbours.add(getLeftNeighbour(body, surroundings));
        return neighbours;
    }

    public static Maybe<IReadBody> getFrontNeighbour(IReadBody body, IReadSurroundings surroundings) {
        Vector2f direction = body.readDirection().forward();
        Vector2f frontPosition = Vector2f.add(body.readPosition(), direction);
        return surroundings.isDirectionBlocked(body, direction) ? new Nothing() : surroundings.readBody(frontPosition);

    }

    public static Maybe<IReadBody> getRightNeighbour(IReadBody body, IReadSurroundings surroundings) {
        Vector2f direction = body.readDirection().forward();
        Vector2f rightPosition = Vector2f.add(body.readPosition(), direction.right());
        return surroundings.isDirectionBlocked(body, direction) ? new Nothing() : surroundings.readBody(rightPosition);
    }

    public static Maybe<IReadBody> getBackNeighbour(IReadBody body, IReadSurroundings surroundings) {
        Vector2f direction = body.readDirection().forward();
        Vector2f backPosition = Vector2f.add(body.readPosition(), direction.backward());
        return surroundings.isDirectionBlocked(body, direction) ? new Nothing() : surroundings.readBody(backPosition);
    }

    public static Maybe<IReadBody> getLeftNeighbour(IReadBody body, IReadSurroundings surroundings) {
        Vector2f direction = body.readDirection().forward();
        Vector2f leftPosition = Vector2f.add(body.readPosition(), direction.left());
        return surroundings.isDirectionBlocked(body, direction) ? new Nothing() : surroundings.readBody(leftPosition);
    }

    public static boolean isFacing(IReadBody body, Vector2f other){
        return Vector2f.equals(Vector2f.add(body.readPosition(), body.readDirection()), other);
    }

    public static boolean isRightOf(IReadBody body, Vector2f other){
        Vector2f right = body.readDirection().right();

        return Vector2f.equals(Vector2f.add(body.readPosition(), right), other);
    }
}
