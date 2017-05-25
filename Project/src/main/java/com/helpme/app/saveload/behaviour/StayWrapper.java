package com.helpme.app.saveload.behaviour;

import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Stay;
import com.helpme.app.utils.interfaces.ILoadable;

/**
 * Created by kopa on 2017-05-19.
 */
public class StayWrapper implements ILoadable<IBehaviour> {

    public StayWrapper(){

    }

    public StayWrapper(Stay stayBehaviour){

    }

    @Override
    public IBehaviour getObject() {
        return BehaviourFactory.createStay(0, null);
    }
}
