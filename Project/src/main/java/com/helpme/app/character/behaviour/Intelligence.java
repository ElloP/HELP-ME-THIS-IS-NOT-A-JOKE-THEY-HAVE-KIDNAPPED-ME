package com.helpme.app.character.behaviour;

import com.helpme.app.character.IMonster;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.ILevel;
import com.helpme.app.world.IReadLevel;

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
        return new IMonster[]{getFrontMonster(monster, level),getRightMonster(monster, level),getBackMonster(monster, level),getLeftMonster(monster, level)};
    }

    public static IMonster getFrontMonster(IMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f frontPosition = Vector2f.add(monster.getPosition(), direction);
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(frontPosition);

    }

    public static IMonster getRightMonster(IMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f rightPosition = Vector2f.add(monster.getPosition(), direction.right());
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(rightPosition);
    }

    public static IMonster getBackMonster(IMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f backPosition = Vector2f.add(monster.getPosition(), direction.backward());
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(backPosition);
    }

    public static IMonster getLeftMonster(IMonster monster, IReadLevel level){
        Vector2f direction = monster.getDirection().forward();
        Vector2f leftPosition = Vector2f.add(monster.getPosition(), direction.left());
        return level.isMonsterBlockedByEdge(monster, direction) ? null : level.getMonster(leftPosition);
    }
}
