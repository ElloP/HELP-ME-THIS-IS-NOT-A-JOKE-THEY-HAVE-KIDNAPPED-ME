package com.helpme.app.game.saveload;

import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.ISurroundings;
import com.helpme.app.game.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.game.model.consciousness.concrete.ConsciousnessFactory;
import com.helpme.app.game.model.consciousness.concrete.Enemy;
import com.helpme.app.game.saveload.behaviour.BehaviourWrapper;
import com.helpme.app.game.saveload.memory.MemoryWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klas on 2017-05-01.
 */
@XmlRootElement(name = "Enemy")
public class EnemyWrapper {
    @XmlElement(name = "body")
    private BodyWrapper bodyWrapper;
    @XmlElement(name = "memory")
    private MemoryWrapper memoryWrapper;

    @XmlElementWrapper(name = "behaviours")
    @XmlElement(name = "behaviour")
    private BehaviourWrapper[] behaviourWrappers;


    public EnemyWrapper(){}

    public EnemyWrapper(Enemy enemy){
        this.bodyWrapper = new BodyWrapper(enemy.getBody());
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

    public IConsciousness getObject(ISurroundings level) {
        List<IBehaviour> behaviours = new ArrayList<>();
        for(BehaviourWrapper behaviourWrapper : behaviourWrappers){
            behaviours.add(behaviourWrapper.getObject());
        }

        return ConsciousnessFactory.createEnemy(bodyWrapper.getObject(), level, memoryWrapper.getObject(), behaviours);
    }
}
