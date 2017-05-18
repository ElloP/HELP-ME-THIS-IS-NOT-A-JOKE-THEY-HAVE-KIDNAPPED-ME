package com.helpme.app.saveload;

import com.helpme.app.world.character.behaviour.Follow;
import com.helpme.app.world.character.behaviour.Stay;
import com.helpme.app.world.character.behaviour.Return;
import com.helpme.app.world.character.behaviour.IBehaviour;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by og on 2017-05-11.
 */
public class BehaviourWrapper implements ILoadable<IBehaviour> {
    FollowWrapper followWrapper;

    public BehaviourWrapper() {
    }

    public BehaviourWrapper(IBehaviour behaviour) {
        if (behaviour instanceof Follow) {
            this.followWrapper = new FollowWrapper((Follow)behaviour);
        } else if (behaviour instanceof Return) {
            this.behavior = "Return";
        } else if (behaviour instanceof Stay) {
            this.behavior = "Stay";
        }
    }

    @XmlElement(name = "Behavior")
    public String getBehavior() {
        return this.behavior;
    }

    public void setBehavior(String behavior) {
        if (behavior.equals("Follow") || behavior.equals("Return")) {
            this.behavior = behavior;
        } else if (behavior.equals("Stay")) {
            this.behavior = "Stay";
        }
    }

    @Override
    public IBehaviour getObject() {
        switch (behavior) {
            case "Follow":
                return new Follow();
            case "Return":
                return new Return();
            default:
                return new Stay();
        }
    }
}
