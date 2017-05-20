package com.helpme.app.saveload;

import com.helpme.app.saveload.behaviour.BehaviourWrapper;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;
import com.helpme.app.world.consciousness.concrete.ConsciousnessFactory;
import com.helpme.app.world.consciousness.concrete.Enemy;
import com.helpme.app.world.consciousness.ISurroundings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klas on 2017-05-01.
 */
public class EnemyWrapper{
    private BodyWrapper bodyWrapper;
    private MemoryWrapper memoryWrapper;
    private BehaviourWrapper[] behaviourWrappers;


    public EnemyWrapper(){}

    public EnemyWrapper(Enemy monster){
        this.bodyWrapper = new BodyWrapper(monster.readBody());
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
    public BehaviourWrapper[] getBehaviours() { return behaviourWrappers; }
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
