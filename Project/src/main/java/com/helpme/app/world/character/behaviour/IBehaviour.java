package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.behaviour.memories.IShortTerm;
import com.helpme.app.world.consciousness.IReadSurroundings;

import java.util.Map;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IBehaviour {
    boolean valid(Map<String, Integer> conditions);
    Maybe<String> update(IReadBody body, IReadSurroundings surroundings, IShortTerm memory);
}
