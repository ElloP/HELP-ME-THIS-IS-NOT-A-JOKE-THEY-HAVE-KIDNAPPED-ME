package com.helpme.app.world.consciousness.behaviour.concrete;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.behaviour.memories.IShortTerm;
import com.helpme.app.world.consciousness.IReadSurroundings;

import java.util.Map;

/**
 * Created by kopa on 2017-04-14.
 */
public class Stay extends Behaviour {

    public Stay(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions) {
        super(priority, preconditions);
    }

    @Override
    public Maybe<String> execute(IReadBody body, IReadSurroundings surroundings, IShortTerm memory) {
        return new Nothing<>();
    }
}
