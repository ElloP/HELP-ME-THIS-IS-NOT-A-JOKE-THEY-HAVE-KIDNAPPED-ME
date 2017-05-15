package com.helpme.app.saveload;

import com.helpme.app.world.character.behaviour.DoNothing;
import com.helpme.app.world.character.behaviour.FollowAndAttack;
import com.helpme.app.world.character.behaviour.GoBack;
import com.helpme.app.world.character.behaviour.IBehaviour;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by og on 2017-05-11.
 */
public class BehaviourWrapper implements ILoadable<IBehaviour>{
    private String behavior;
    public BehaviourWrapper(){}

    public BehaviourWrapper(IBehaviour behave){
        if(behave instanceof FollowAndAttack){
            this.behavior = "FollowAndAttack";
        }
        else if(behave instanceof GoBack){
            this.behavior = "GoBack";
        } else {
            this.behavior = "DoNothing";
        }
    }
    @XmlElement(name="Behavior")
    public String getBehavior(){
        return this.behavior;
    }
    public void setBehavior(String behavior){
        if(behavior == "FollowAndAttack" || behavior == "GoBack" ) this.behavior = behavior;
        else this.behavior = "DoNothing";
    }

    @Override
    public IBehaviour getObject() {
        switch (behavior){
            case "FollowAndAttack": return new FollowAndAttack();
            case "GoBack": return new GoBack();
            default: return new DoNothing();
        }
    }
}
