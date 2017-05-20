package com.helpme.app.saveload.behaviour;

import com.helpme.app.saveload.ILoadable;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.concrete.*;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by og on 2017-05-11.
 */
public class BehaviourWrapper implements ILoadable<IBehaviour> {
    private int priority;
    private PreconditionWrapper[] preconditionWrappers;

    private FollowWrapper followWrapper;
    private ReturnWrapper returnWrapper;
    private StayWrapper stayWrapper;
    private AttackWrapper attackWrapper;

    public BehaviourWrapper() {
    }

    public BehaviourWrapper(IBehaviour behaviour) {
        this.priority = behaviour.getPriority();


        if (behaviour instanceof Follow) {
            this.followWrapper = new FollowWrapper((Follow) behaviour);
        } else if (behaviour instanceof Return) {
            this.returnWrapper = new ReturnWrapper((Return) behaviour);
        } else if (behaviour instanceof Stay) {
            this.stayWrapper = new StayWrapper((Stay) behaviour);
        } else if (behaviour instanceof AttackWrapper) {
            this.attackWrapper = new AttackWrapper((Attack) behaviour);
        }
    }

    @XmlElement(name = "priority")
    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @XmlElementWrapper(name = "preconditions")
    @XmlElement(name = "precondition")
    public PreconditionWrapper[] getPreconditions(){
        return preconditionWrappers;
    }
    public void setPreconditions(PreconditionWrapper[] preconditionWrappers){
        this.preconditionWrappers = new PreconditionWrapper[preconditionWrappers.length];
        for(int i = 0; i < preconditionWrappers.length; i++){
            this.preconditionWrappers[i] = preconditionWrappers[i];
        }
    }

    @XmlElement(name = "follow")
    public FollowWrapper getFollow() {
        return this.followWrapper;
    }

    public void setFollow(FollowWrapper followWrapper) {
        this.followWrapper = followWrapper;
    }

    @XmlElement(name = "return")
    public ReturnWrapper getReturn() {
        return this.returnWrapper;
    }

    public void setReturn(ReturnWrapper returnWrapper) {
        this.returnWrapper = returnWrapper;
    }

    @XmlElement(name = "stay")
    public StayWrapper getStay() {
        return this.stayWrapper;
    }

    public void setStay(StayWrapper stayWrapper) {
        this.stayWrapper = stayWrapper;
    }

    @XmlElement(name = "attack")
    public AttackWrapper getAttack() {
        return this.attackWrapper;
    }

    public void setAttack(AttackWrapper attackWrapper) {
        this.attackWrapper = attackWrapper;
    }

    @Override
    public IBehaviour getObject() {
        IBehaviour behaviour = null;
        if (followWrapper != null) {
            behaviour = followWrapper.getObject();
        } else if (returnWrapper != null) {
            behaviour = returnWrapper.getObject();
        } else if (attackWrapper != null) {
            behaviour = attackWrapper.getObject();
        } else {
            behaviour = stayWrapper.getObject();
        }

        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<String, Tuple2<Integer, Comparison>>(){
            {
                for(PreconditionWrapper preconditionWrapper : preconditionWrappers){
                    Entry<String, Tuple2<Integer, Comparison>> entry = preconditionWrapper.getObject();
                    put(entry.getKey(), entry.getValue());
                }
            }
        };

        behaviour.setPriority(priority);
        behaviour.setPreconditions(preconditions);
        return behaviour;
    }
}
