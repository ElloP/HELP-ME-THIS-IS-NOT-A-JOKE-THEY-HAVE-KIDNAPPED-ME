package com.helpme.app.world.consciousness.behaviour.concrete;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.behaviour.concrete.Behaviour;
import com.helpme.app.world.consciousness.behaviour.concrete.Comparison;
import com.helpme.app.world.consciousness.behaviour.memories.IShortTerm;
import com.helpme.app.world.consciousness.IReadSurroundings;

import java.util.Map;

/**
 * Created by Jesper on 2017-04-20.
 */
public class Return extends Behaviour {
    private String returnEvent;

    public Return(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions, String returnEvent) {
        super(priority, preconditions);
        this.returnEvent = returnEvent;
    }

    public String getReturnEvent(){
        return returnEvent;
    }

    @Override
    public void reset(IShortTerm memory){
        memory.updateShortTermMemory(returnEvent, 0);
    }

    @Override
    public Maybe<String> execute(IReadBody body, IReadSurroundings surroundings, IShortTerm memory) {
        if(returned(body, surroundings)){
            memory.updateShortTermMemory(returnEvent, 1);
            return new Nothing<>();
        }

        Vector2f nextPosition = surroundings.getShortestPath(body.readPosition(), body.readStartingPosition()).b;
        return new Just<>(moveTowards(body, nextPosition));
    }

    private boolean returned(IReadBody body, IReadSurroundings surroundings){
        int cost = surroundings.getShortestPath(body.readPosition(), body.readStartingPosition()).c;
        return cost <= 0;
    }
}
