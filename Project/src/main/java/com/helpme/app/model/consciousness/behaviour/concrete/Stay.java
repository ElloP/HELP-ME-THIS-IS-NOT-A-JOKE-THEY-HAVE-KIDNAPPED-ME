package com.helpme.app.model.consciousness.behaviour.concrete;

import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.model.body.IReadBody;
import com.helpme.app.model.consciousness.IConsciousness;
import com.helpme.app.model.consciousness.behaviour.Comparison;
import com.helpme.app.model.consciousness.behaviour.memories.IShortTerm;
import com.helpme.app.model.consciousness.IReadSurroundings;

import java.util.Map;

/**
 * Created by kopa on 2017-04-14.
 */
public class Stay extends Behaviour {

    public Stay(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions) {
        super(priority, preconditions);
    }

    @Override
    public Maybe<IAction<IConsciousness>> execute(IReadBody body, IReadSurroundings surroundings, IShortTerm memory) {
        return new Nothing<>();
    }
}
