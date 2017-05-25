package com.helpme.app.saveload.behaviour;

import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Return;
import com.helpme.app.utils.interfaces.ILoadable;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-19.
 */
public class ReturnWrapper implements ILoadable<IBehaviour> {

    String returnedEvent;
    String returningEvent;

    public ReturnWrapper(){

    }

    public ReturnWrapper(Return returnBehaviour){
        returnedEvent = returnBehaviour.getReturnedEvent();
        returningEvent = returnBehaviour.getReturningEvent();
    }

    @XmlElement(name = "returned_event")
    public String getReturnedEvent(){ return returnedEvent; }
    public void setReturnedEvent(String returnedEvent){
        this.returnedEvent = returnedEvent;
    }

    @XmlElement(name = "returning_event")
    public String getReturningEvent(){ return returningEvent; }
    public void setReturningEvent(String returningEvent){
        this.returningEvent = returningEvent;
    }

    @Override
    public IBehaviour getObject() {
        return BehaviourFactory.createReturn(0, null, returningEvent, returnedEvent);
    }
}
