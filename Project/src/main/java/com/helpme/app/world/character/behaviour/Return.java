package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.consciousness.IReadSurroundings;


import java.util.List;

/**
 * Created by Jesper on 2017-04-20.
 */
public class GoBack implements IBehaviour {

    public Maybe update(IReadBody body, IReadSurroundings surroundings) {
        return goBack(body, surroundings);
    }

    private Maybe goBack(IReadBody body, IReadSurroundings surroundings){
        Tuple3<List<Vector2f>, Vector2f, Integer> path = surroundings.getShortestPath(body.readPosition(), body.readStartingPosition());
        int cost = path.c;
        if (cost > 0){
            Vector2f nextPos = path.b;
            return new Just(Intelligence.moveOrRotateAction(body, nextPos));
        }else{
            return new Nothing();
        }
    }
}
