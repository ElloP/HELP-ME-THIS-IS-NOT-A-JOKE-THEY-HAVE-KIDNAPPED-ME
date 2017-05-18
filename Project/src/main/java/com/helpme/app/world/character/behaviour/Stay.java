package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.behaviour.memories.IShortTerm;
import com.helpme.app.world.consciousness.IReadSurroundings;

import java.util.Map;

/**
 * Created by kopa on 2017-04-14.
 */
public class Stay extends Behaviour {

    public Stay(String name, Map<String, Tuple2<Integer, Comparison>> preconditions) {
        super(name, preconditions);
    }

    @Override
    public Maybe<String> update(IReadBody body, IReadSurroundings surroundings, IShortTerm memory) {
        return new Nothing<>();
    }
}
