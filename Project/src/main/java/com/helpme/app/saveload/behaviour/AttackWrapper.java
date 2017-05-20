package com.helpme.app.saveload.behaviour;

import com.helpme.app.saveload.ILoadable;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.behaviour.concrete.Attack;
import com.helpme.app.world.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.world.consciousness.behaviour.concrete.Follow;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-19.
 */
public class AttackWrapper implements ILoadable<IBehaviour> {
    String attackEvent;

    public AttackWrapper() {

    }

    public AttackWrapper(Attack attackBehaviour) {
        attackEvent = attackBehaviour.getAttackEvent();
    }

    @XmlElement(name = "attack_event")
    public String getAttackEvent() {
        return attackEvent;
    }

    public void setAttackEvent(String attackEvent) {
        this.attackEvent = attackEvent;
    }

    @Override
    public IBehaviour getObject() {
        return BehaviourFactory.createAttack(0,null, attackEvent);
    }
}
