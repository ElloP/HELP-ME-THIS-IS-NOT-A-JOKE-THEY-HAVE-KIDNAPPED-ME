package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.level.read.IReadLevel;


import java.util.List;

/**
 * Created by Jesper on 2017-04-20.
 */
public class GoBack implements IBehaviour {
    @Override
    public Maybe update(IReadMonster monster, IReadLevel level) {
        return goBack(monster, level);
    }

    private Maybe goBack(IReadMonster monster, IReadLevel level){
        Tuple3<List<Vector2f>, Vector2f, Integer> path = level.getShortestPath(monster.readPosition(), monster.readStartingPosition());
        int cost = path.c;
        if (cost > 0){
            Vector2f nextPos = path.b;
            return new Just(Intelligence.moveOrRotateAction(monster, nextPos));
        }else{
            return new Nothing();
        }
    }
}
