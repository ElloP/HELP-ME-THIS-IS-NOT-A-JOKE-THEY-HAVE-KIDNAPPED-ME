package com.helpme.app.saveload;

import com.helpme.app.world.consciousness.concrete.Enemy;
import com.helpme.app.world.consciousness.ISurroundings;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Klas on 2017-05-01.
 */
public class EnemyWrapper{
    private BodyWrapper body;
    private BehaviourWrapper defaultBehavior, currentBehavior;


    public EnemyWrapper(){}

    public EnemyWrapper(Enemy monster){
        this.body = new BodyWrapper(monster.readBody());
        this.currentBehavior = new BehaviourWrapper(monster.getBehaviour());
        this.defaultBehavior = new BehaviourWrapper(monster.getDefaultBehavior());
    }

    public String toString(){
        String result = "";
        result += body.toString();
        return result;
    }
    @XmlElement(name="Body")
    public BodyWrapper getBody(){
        return body;
    }
    public void setBody(BodyWrapper body){
        this.body = body;
    }

    @XmlElement(name="Default_Behavior")
    public void setDefaultBehavior(BehaviourWrapper defaultBehavior) {
        this.defaultBehavior = defaultBehavior;
    }
    @XmlElement(name="Current_Behavior")
    public void setCurrentBehavior(BehaviourWrapper currentBehavior) {
        this.currentBehavior = currentBehavior;
    }
    public BehaviourWrapper getDefaultBehavior() {
        return defaultBehavior;

    }
    public BehaviourWrapper getCurrentBehavior() {
        return currentBehavior;
    }

    public Enemy getObject(ISurroundings level) {
        return new Enemy(body.getObject(), level, currentBehavior.getObject(),defaultBehavior.getObject()); //fuck this
    }


}
