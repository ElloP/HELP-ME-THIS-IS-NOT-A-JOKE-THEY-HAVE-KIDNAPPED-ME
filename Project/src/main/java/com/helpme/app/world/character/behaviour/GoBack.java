package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.level.IReadLevel;


import java.util.List;

/**
 * Created by Jesper on 2017-04-20.
 */
public class GoBack implements IBehaviour {
    @Override
    public Either<IBehaviour, IAction<IMonster>> update(IReadMonster monster, IReadLevel level) {
        return goBack(monster, level);
    }

    private Either goBack(IReadMonster monster, IReadLevel level){
        Tuple3<List<Vector2f>, Vector2f, Integer> path = level.getShortestPath(monster.readPosition(), monster.readStartingPosition());
        int cost = path.c;
        if (cost > 0){
            Vector2f nextPos = path.b;
            if (Intelligence.isMonsterFacing(monster, nextPos)){
                return Action.moveForwardAction();
            } else if (Intelligence.isLeftOf(monster, nextPos)){
                return Action.rotateRight();
            } else {
                return Action.rotateLeft();
            }
        }else{
            return new Left<IBehaviour, IAction<IMonster>>(new DoNothing());
        }
    }
}
