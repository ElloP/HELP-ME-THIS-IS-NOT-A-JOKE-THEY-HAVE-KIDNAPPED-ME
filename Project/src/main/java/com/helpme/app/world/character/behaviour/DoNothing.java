package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.consciousness.IReadSurroundings;

/**
 * Created by kopa on 2017-04-14.
 */
public class DoNothing implements IBehaviour {
    public Maybe update(IReadBody body, IReadSurroundings surroundings) {
        return new Just(new Left<>(new DoNothing()));
    }
}
