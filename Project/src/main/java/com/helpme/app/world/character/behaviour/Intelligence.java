package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.level.IReadLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kopa on 2017-04-14.
 */
public abstract class Intelligence implements IBehaviour {

    private Intelligence() {

    }

    public static boolean isMonsterNextTo(IReadMonster monster, IReadMonster potentialNeighbour, IReadLevel level) {
        for (Maybe<IReadMonster> maybeNeighbour : getMonsterNeighbours(monster, level)) {
            if(maybeNeighbour.check(n -> potentialNeighbour.equals(n))){
                return true;
            }
        }
        return false;
    }

    public static List<Maybe<IReadMonster>> getMonsterNeighbours(IReadMonster monster, IReadLevel level) {
        return new ArrayList(4) {
            {
                add(getMonsterFrontNeighbour(monster, level));
                add(getMonsterRightNeighbour(monster, level));
                add(getMonsterBackNeighbour(monster, level));
                add(getMonsterLeftNeighbour(monster, level));
            }
        };
    }

    public static Maybe<IReadMonster> getMonsterFrontNeighbour(IReadMonster monster, IReadLevel level) {
        Vector2f direction = monster.readDirection().forward();
        Vector2f frontPosition = Vector2f.add(monster.readPosition(), direction);
        return level.isMonsterBlockedByEdge(monster, direction) ? new Nothing() : level.readMonster(frontPosition);

    }

    public static Maybe<IReadMonster> getMonsterRightNeighbour(IReadMonster monster, IReadLevel level) {
        Vector2f direction = monster.readDirection().forward();
        Vector2f rightPosition = Vector2f.add(monster.readPosition(), direction.right());
        return level.isMonsterBlockedByEdge(monster, direction) ? new Nothing() : level.readMonster(rightPosition);
    }

    public static Maybe<IReadMonster> getMonsterBackNeighbour(IReadMonster monster, IReadLevel level) {
        Vector2f direction = monster.readDirection().forward();
        Vector2f backPosition = Vector2f.add(monster.readPosition(), direction.backward());
        return level.isMonsterBlockedByEdge(monster, direction) ? new Nothing() : level.readMonster(backPosition);
    }

    public static Maybe<IReadMonster> getMonsterLeftNeighbour(IReadMonster monster, IReadLevel level) {
        Vector2f direction = monster.readDirection().forward();
        Vector2f leftPosition = Vector2f.add(monster.readPosition(), direction.left());
        return level.isMonsterBlockedByEdge(monster, direction) ? new Nothing() : level.readMonster(leftPosition);
    }

    public static boolean isMonsterFacing(IReadMonster monster, Vector2f other){
        return Vector2f.equals(Vector2f.add(monster.readPosition(), monster.readDirection()), other);
    }

    public static boolean isLeftOf(IReadMonster monster, Vector2f other){
        Vector2f right = monster.readDirection().right();

        return Vector2f.equals(Vector2f.add(monster.readPosition(), right), other);
    }

    public static Either moveOrRotateAction(IReadMonster monster, Vector2f nextPos) {
        if (Intelligence.isMonsterFacing(monster, nextPos)){
            return Action.moveForwardAction();
        } else if (Intelligence.isLeftOf(monster, nextPos)){
            return Action.rotateRight();
        } else {
            return Action.rotateLeft();
        }
    }
}
