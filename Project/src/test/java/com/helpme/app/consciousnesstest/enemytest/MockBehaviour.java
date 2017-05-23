package com.helpme.app.consciousnesstest.enemytest;

import com.helpme.app.model.body.IReadBody;
import com.helpme.app.model.consciousness.IConsciousness;
import com.helpme.app.model.consciousness.IReadSurroundings;
import com.helpme.app.model.consciousness.behaviour.Comparison;
import com.helpme.app.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.model.consciousness.behaviour.memory.IShortTerm;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;

import java.util.Map;

/**
 * Created by kopa on 2017-05-22.
 */
public class MockBehaviour implements IBehaviour {
    int priority;
    boolean valid;
    int reset;
    int execute;

    public MockBehaviour(int priority, boolean valid){
        this.priority = priority;
        this.valid = valid;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Map<String, Tuple2<Integer, Comparison>> getPreconditions() {
        return null;
    }

    @Override
    public void setPriority(int priority) {

    }

    @Override
    public void setPreconditions(Map<String, Tuple2<Integer, Comparison>> preconditions) {

    }

    @Override
    public boolean valid(Map<String, Integer> conditions) {
        return valid;
    }

    @Override
    public void reset(IShortTerm memory) {
        reset++;
    }

    @Override
    public Maybe<IAction<IConsciousness>> execute(IReadBody body, IReadSurroundings surroundings, IShortTerm memory) {
        execute++;
        return new Nothing<>();
    }
}
