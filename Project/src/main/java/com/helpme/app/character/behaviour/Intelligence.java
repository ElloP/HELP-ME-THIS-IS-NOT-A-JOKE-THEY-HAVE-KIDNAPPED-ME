package com.helpme.app.character.behaviour;

import com.helpme.app.character.IMonster;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.level.IReadLevel;

/**
 * Created by kopa on 2017-04-14.
 */
public abstract class Intelligence implements IBehaviour{

    private Intelligence(){

    }

    public static boolean isMonsterNextTo(IMonster monster, IMonster potentialNeighbour, IReadLevel level){
        for(IMonster neighbour : getMonsterNeighbours(monster, level)){
            if(potentialNeighbour.equals(neighbour)) {
                return true;
            }
        }
        return false;
    }

    public static IMonster[] getMonsterNeighbours(IMonster monster, IReadLevel level){
        return new IMonster[]{getMonsterFrontNeighbour(monster, level), getMonsterRightNeighbour(monster, level), getMonsterBackNeighbour(monster, level), getMonsterLeftNeighbour(monster, level)};
    }

    public static IMonster getMonsterFrontNeighbour(IMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f frontPosition = Vector2f.add(monster.getPosition(), direction);
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(frontPosition);

    }

    public static IMonster getMonsterRightNeighbour(IMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f rightPosition = Vector2f.add(monster.getPosition(), direction.right());
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(rightPosition);
    }

    public static IMonster getMonsterBackNeighbour(IMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f backPosition = Vector2f.add(monster.getPosition(), direction.backward());
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(backPosition);
    }

    public static IMonster getMonsterLeftNeighbour(IMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f leftPosition = Vector2f.add(monster.getPosition(), direction.left());
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(leftPosition);
    }
}
