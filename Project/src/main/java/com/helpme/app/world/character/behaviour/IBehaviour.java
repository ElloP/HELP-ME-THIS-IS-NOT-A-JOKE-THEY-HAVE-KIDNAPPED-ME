package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.level.read.IReadLevel;

/**
 * Created by kopa on 2017-04-14.
 */
public interface IBehaviour {

    Maybe<Either<IBehaviour, IAction<IMonster>>> update(IReadMonster monster, IReadLevel level);
}
