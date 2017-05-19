package com.helpme.app.world.consciousness.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.IReadSurroundings;
import com.helpme.app.utils.maybe.Maybe;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IBehaviour {
    Maybe<Either<IBehaviour, IAction<IBody>>> update(IReadBody body, IReadSurroundings surroundings);
}
