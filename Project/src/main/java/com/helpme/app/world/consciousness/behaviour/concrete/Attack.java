package com.helpme.app.world.consciousness.behaviour.concrete;

import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.IReadSurroundings;
import com.helpme.app.world.consciousness.behaviour.memories.IShortTerm;

import java.util.Map;

/**
 * Created by kopa on 2017-05-19.
 */
public class Attack extends Behaviour {
    private String attackEvent;

    public Attack(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions, String attackEvent) {
        super(priority, preconditions);
        this.attackEvent = attackEvent;
    }

    public String getAttackEvent(){
        return attackEvent;
    }

    @Override
    public void reset(IShortTerm memory){
        memory.updateShortTermMemory(attackEvent, 0);
    }

    @Override
    public Maybe<String> execute(IReadBody body, IReadSurroundings surroundings, IShortTerm memory) {
        if(surroundings.readFacing(body).isJust()){
            memory.updateShortTermMemory(attackEvent, 1);
        }
        return new Just<>("attack");
    }
}
