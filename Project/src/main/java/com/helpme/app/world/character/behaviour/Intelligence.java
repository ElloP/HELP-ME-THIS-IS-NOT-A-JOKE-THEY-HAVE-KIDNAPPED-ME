package com.helpme.app.world.character.behaviour;

import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.level.IReadLevel;

/**
 * Created by kopa on 2017-04-14.
 */
public abstract class Intelligence implements IBehaviour{

    private Intelligence(){

    }

    public static boolean isMonsterNextTo(IReadMonster monster, IReadMonster potentialNeighbour, IReadLevel level){
        for(IReadMonster neighbour : getMonsterNeighbours(monster, level)){
            if(potentialNeighbour.equals(neighbour)) {
                return true;
            }
        }
        return false;
    }

    public static IReadMonster[] getMonsterNeighbours(IReadMonster monster, IReadLevel level){
        return new IReadMonster[]{getMonsterFrontNeighbour(monster, level), getMonsterRightNeighbour(monster, level), getMonsterBackNeighbour(monster, level), getMonsterLeftNeighbour(monster, level)};
    }

    public static IReadMonster getMonsterFrontNeighbour(IReadMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f frontPosition = Vector2f.add(monster.getPosition(), direction);
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(frontPosition);

    }

    public static IReadMonster getMonsterRightNeighbour(IReadMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f rightPosition = Vector2f.add(monster.getPosition(), direction.right());
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(rightPosition);
    }

    public static IReadMonster getMonsterBackNeighbour(IReadMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f backPosition = Vector2f.add(monster.getPosition(), direction.backward());
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(backPosition);
    }

    public static IReadMonster getMonsterLeftNeighbour(IReadMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f leftPosition = Vector2f.add(monster.getPosition(), direction.left());
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(leftPosition);
    }
}
