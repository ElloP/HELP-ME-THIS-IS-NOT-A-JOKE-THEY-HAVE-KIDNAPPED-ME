package com.helpme.app.saveload.behaviour;

import com.helpme.app.saveload.ILoadable;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.world.consciousness.behaviour.concrete.Return;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-19.
 */
public class ReturnWrapper implements ILoadable<IBehaviour> {

    String returnEvent;

    public ReturnWrapper(){

    }

    public ReturnWrapper(Return returnBehaviour){
        returnEvent = returnBehaviour.getReturnEvent();
    }

    @XmlElement(name = "return_event")
    public String getReturnEvent(){ return returnEvent; }
    public void setReturnEvent(String returnEvent){
        this.returnEvent = returnEvent;
    }

    @Override
    public IBehaviour getObject() {
        return BehaviourFactory.createReturn(0, null, returnEvent);
    }
}
