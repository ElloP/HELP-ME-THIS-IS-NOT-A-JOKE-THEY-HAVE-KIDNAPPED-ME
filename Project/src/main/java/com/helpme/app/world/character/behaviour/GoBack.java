package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.consciousness.ISurroundings;


import java.util.List;

/**
 * Created by Jesper on 2017-04-20.
 */
public class GoBack implements IBehaviour {
    @Override
    public Either<IBehaviour, IAction<IBody>> update(IReadBody monster, ISurroundings level) {
        return goBack(monster, level);
    }

    private Either goBack(IReadBody monster, ISurroundings level){
        Tuple3<List<Vector2f>, Vector2f, Integer> path = level.getShortestPath(monster.readPosition(), monster.readStartingPosition());
        int cost = path.c;
        if (cost > 0){
            Vector2f nextPos = path.b;
            return Intelligence.moveOrRotateAction(monster, nextPos);
        }else{
            return new Left<IBehaviour, IAction<IBody>>(new DoNothing());
        }
    }
}
