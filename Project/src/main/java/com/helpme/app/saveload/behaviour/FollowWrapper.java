package com.helpme.app.saveload.behaviour;

import com.helpme.app.utils.interfaces.ILoadable;
import com.helpme.app.model.consciousness.behaviour.IBehaviour;
import com.helpme.app.model.consciousness.behaviour.concrete.BehaviourFactory;
import com.helpme.app.model.consciousness.behaviour.concrete.Follow;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by kopa on 2017-05-17.
 */
public class FollowWrapper implements ILoadable<IBehaviour> {
    private int followingDistance;
    private String foundEvent;
    private String followingEvent;
    private String lostEvent;


    public FollowWrapper() {

    }

    public FollowWrapper(Follow followBehaviour) {
        followingDistance = followBehaviour.getFollowingDistance();
        foundEvent = followBehaviour.getFoundEvent();
        followingEvent = followBehaviour.getFollowingEvent();
        lostEvent = followBehaviour.getLostEvent();
    }

    @XmlElement(name = "following_distance")
    public int getFollowingDistance() {
        return followingDistance;
    }

    public void setFollowingDistance(int followingDistance) {
        this.followingDistance = followingDistance;
    }

    @XmlElement(name = "found_event")
    public String getFoundEvent() {
        return followingEvent;
    }

    public void setFoundEvent(String foundEvent) {
        this.foundEvent = foundEvent;
    }

    @XmlElement(name = "following_event")
    public String getFollowingEvent() {
        return followingEvent;
    }

    public void setFollowingEvent(String followingEvent) {
        this.followingEvent = followingEvent;
    }

    @XmlElement(name = "lost_event")
    public String getLostEvent() {
        return lostEvent;
    }

    public void setLostEvent(String lostEvent) {
        this.lostEvent = lostEvent;
    }

    @Override
    public IBehaviour getObject() {
        return BehaviourFactory.createFollow(0, null, followingDistance, foundEvent, followingEvent, lostEvent);
    }
}
