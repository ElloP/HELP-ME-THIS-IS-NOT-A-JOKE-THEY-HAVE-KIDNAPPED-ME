package com.helpme.app.saveload.behaviour;

import com.helpme.app.saveload.ILoadable;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.concrete.Stay;

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
        return null;
    }
}
