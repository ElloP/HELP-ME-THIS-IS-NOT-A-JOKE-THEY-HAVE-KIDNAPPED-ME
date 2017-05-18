package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.behaviour.memories.IShortTerm;
import com.helpme.app.world.consciousness.IReadSurroundings;

import java.util.Map;

/**
 * Created by Jesper on 2017-04-20.
 */
public class Return extends Behaviour {

    public Return(String name, Map<String, Tuple2<Integer, Comparison>> preconditions) {
        super(name, preconditions);
    }

    @Override
    public Maybe<String> update(IReadBody body, IReadSurroundings surroundings, IShortTerm memory) {
        if(returned(body, surroundings)){
            return new Just<>("return");
        }

        return new Nothing<>();
    }

    private boolean returned(IReadBody body, IReadSurroundings surroundings){
        int cost = surroundings.getShortestPath(body.readPosition(), body.readStartingPosition()).c;
        return cost <= 0;
    }
}
