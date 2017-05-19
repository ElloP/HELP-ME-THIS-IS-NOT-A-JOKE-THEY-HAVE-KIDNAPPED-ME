package com.helpme.app.thoughttest;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.IReadSurroundings;

/**
 * Created by kopa on 2017-05-14.
 */
public class MockBehaviour implements IBehaviour {
    @Override
    public Maybe<Either<IBehaviour, IAction<IBody>>> update(IReadBody body, IReadSurroundings surroundings) {
        return null;
    }
}
