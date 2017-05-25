package com.helpme.app.saveload;

import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.ISurroundings;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.concrete.ConsciousnessFactory;
import com.helpme.app.game.model.consciousness.concrete.Enemy;
import com.helpme.app.saveload.behaviour.BehaviourWrapper;
import com.helpme.app.saveload.memory.MemoryWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klas on 2017-05-01.
 */
public class EnemyWrapper {
    private BodyWrapper bodyWrapper;
    private MemoryWrapper memoryWrapper;
    private BehaviourWrapper[] behaviourWrappers;


    public EnemyWrapper(){}

    public EnemyWrapper(Enemy enemy){
        this.bodyWrapper = new BodyWrapper(enemy.readBody());
        this.memoryWrapper = new MemoryWrapper(enemy.readMemory());
        List<IBehaviour> behaviours = enemy.getBehaviours();
        behaviourWrappers = new BehaviourWrapper[behaviours.size()];
        for(int i = 0; i < behaviours.size(); i++){
            behaviourWrappers[i] = new BehaviourWrapper(behaviours.get(i));
        }
    }

    public String toString(){
        String result = "";
        result += bodyWrapper.toString();
        return result;
    }
    @XmlElement(name="body")
    public BodyWrapper getBody(){
        return bodyWrapper;
    }
    public void setBody(BodyWrapper body){
        this.bodyWrapper = body;
    }

    @XmlElementWrapper(name = "behaviours")
    @XmlElement(name = "behaviour")
    public BehaviourWrapper[] getBehaviours() { return behaviourWrappers.clone(); }
    public void setBehaviours(BehaviourWrapper[] behaviours){
        this.behaviourWrappers = new BehaviourWrapper[behaviours.length];
        for(int i = 0; i < behaviours.length; i++){
            this.behaviourWrappers[i] = behaviours[i];
        }
    }

    @XmlElement(name = "memory")
    public MemoryWrapper getMemory(){ return memoryWrapper; }
    public void setMemory(MemoryWrapper memoryWrapper){
        this.memoryWrapper = memoryWrapper;
    }

    public IConsciousness getObject(ISurroundings level) {
        List<IBehaviour> behaviours = new ArrayList<>();
        for(BehaviourWrapper behaviourWrapper : behaviourWrappers){
            behaviours.add(behaviourWrapper.getObject());
        }

        return ConsciousnessFactory.createEnemy(bodyWrapper.getObject(), level, memoryWrapper.getObject(), behaviours);
    }
}
