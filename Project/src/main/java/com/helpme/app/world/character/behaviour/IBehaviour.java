package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.consciousness.ISurroundings;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IBehaviour {

    Either<IBehaviour, IAction<IBody>> update(IReadBody monster, ISurroundings level);
}
