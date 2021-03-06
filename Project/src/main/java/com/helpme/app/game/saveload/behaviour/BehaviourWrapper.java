package com.helpme.app.game.saveload.behaviour;

import com.helpme.app.game.model.consciousness.behaviour.Comparison;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Attack;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Follow;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Return;
import com.helpme.app.game.model.consciousness.behaviour.concrete.Stay;
import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.utils.tuple.Tuple2;

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
        } else if (behaviour instanceof Attack) {
            this.attackWrapper = new AttackWrapper((Attack) behaviour);
        }

        this.preconditionWrappers = new PreconditionWrapper[behaviour.getPreconditions().size()];
        int i = 0;
        for(Map.Entry<String, Tuple2<Integer, Comparison>> entry : behaviour.getPreconditions().entrySet()){
            Tuple2<Integer, Comparison> tuple = entry.getValue();
            this.preconditionWrappers[i] = new PreconditionWrapper(entry.getKey(), tuple.a, tuple.b);
            i++;
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
        return preconditionWrappers == null ? null :preconditionWrappers.clone();
    }
    public void setPreconditions(PreconditionWrapper[] preconditionWrappers){
        this.preconditionWrappers = new PreconditionWrapper[preconditionWrappers.length];
        System.arraycopy(preconditionWrappers, 0, this.preconditionWrappers, 0, preconditionWrappers.length);
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

        Map<String, Tuple2<Integer, Comparison>> preconditions = new HashMap<>();
        if(preconditionWrappers != null){
            for(PreconditionWrapper preconditionWrapper : preconditionWrappers){
                Map.Entry<String, Tuple2<Integer, Comparison>> entry = preconditionWrapper.getObject();
                preconditions.put(entry.getKey(), entry.getValue());
            }
        }

        behaviour.setPriority(priority);
        behaviour.setPreconditions(preconditions);
        return behaviour;
    }
}
