package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.consciousness.ISurroundings;

/**
 * Created by kopa on 2017-04-14.
 */
public class DoNothing implements IBehaviour {
    @Override
    public Either update(IReadBody monster, ISurroundings level) {
        return new Left<IBehaviour, IAction<IBody>>(new DoNothing());
    }
}
