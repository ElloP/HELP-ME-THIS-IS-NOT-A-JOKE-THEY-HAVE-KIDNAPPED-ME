package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.maybe.Just;
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
            if(maybeNeighbour instanceof Just){
                IReadMonster neighbour = ((Just<IReadMonster>) maybeNeighbour).getValue();
                if (potentialNeighbour.equals(neighbour)) {
                    return true;
                }
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
        Vector2f direction = monster.getDirection().forward();
        Vector2f frontPosition = Vector2f.add(monster.getPosition(), direction);
        return level.isMonsterBlockedByEdge(monster, direction) ? new Nothing() : level.getMonster(frontPosition);

    }

    public static Maybe<IReadMonster> getMonsterRightNeighbour(IReadMonster monster, IReadLevel level) {
        Vector2f direction = monster.getDirection().forward();
        Vector2f rightPosition = Vector2f.add(monster.getPosition(), direction.right());
        return level.isMonsterBlockedByEdge(monster, direction) ? new Nothing() : level.getMonster(rightPosition);
    }

    public static Maybe<IReadMonster> getMonsterBackNeighbour(IReadMonster monster, IReadLevel level) {
        Vector2f direction = monster.getDirection().forward();
        Vector2f backPosition = Vector2f.add(monster.getPosition(), direction.backward());
        return level.isMonsterBlockedByEdge(monster, direction) ? new Nothing() : level.getMonster(backPosition);
    }

    public static Maybe<IReadMonster> getMonsterLeftNeighbour(IReadMonster monster, IReadLevel level) {
        Vector2f direction = monster.getDirection().forward();
        Vector2f leftPosition = Vector2f.add(monster.getPosition(), direction.left());
        return level.isMonsterBlockedByEdge(monster, direction) ? new Nothing() : level.getMonster(leftPosition);
    }
}
