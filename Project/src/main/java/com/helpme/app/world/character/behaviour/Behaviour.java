package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.behaviour.memories.IShortTerm;
import com.helpme.app.world.consciousness.IReadSurroundings;

import java.util.Map;

/**
 * Created by kopa on 2017-05-17.
 */
public abstract class Behaviour implements IBehaviour {
    String name;
    Map<String, Tuple2<Integer, Comparison>> preconditions;

    public Behaviour(String name, Map<String, Tuple2<Integer, Comparison>> preconditions) {
        this.name = name;
        this.preconditions = preconditions;
    }

    @Override
    public boolean valid(Map<String, Integer> conditions) {
        for (Map.Entry<String, Tuple2<Integer, Comparison>> entry : preconditions.entrySet()) {
            Integer value = conditions.get(entry.getKey());

            if (value == null) return false;

            switch (entry.getValue().b) {
                case MORE_THAN:
                    if (entry.getValue().a <= value) return false;
                case LESS_THAN:
                    if (entry.getValue().a >= value) return false;
                case EQUAL:
                    if (entry.getValue().a != value) return false;
            }
        }

        return true;
    }

    @Override
    public abstract Maybe<String> update(IReadBody body, IReadSurroundings surroundings, IShortTerm memory);

}
