package com.helpme.app.game.model.consciousness.behaviour.concrete;

import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.consciousness.IReadSurroundings;
import com.helpme.app.game.model.consciousness.behaviour.Comparison;
import com.helpme.app.game.model.consciousness.memory.IShortTerm;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;

import java.util.Map;

/**
 * Created by kopa on 2017-05-16.
 */
public class Follow extends Behaviour {
    private int followingDistance;
    private String foundEvent;
    private String followingEvent;
    private String lostEvent;

    public Follow(int priority, Map<String, Tuple2<Integer, Comparison>> preconditions, int followingDistance, String foundEvent, String followingEvent, String lostEvent) {
        super(priority, preconditions);
        this.followingDistance = followingDistance;
        this.foundEvent = foundEvent;
        this.followingEvent = followingEvent;
        this.lostEvent = lostEvent;
    }

    public int getFollowingDistance() {
        return followingDistance;
    }

    public String getFoundEvent() {
        return foundEvent;
    }

    public String getFollowingEvent() {
        return followingEvent;
    }

    public String getLostEvent() {
        return lostEvent;
    }

    @Override
    public void reset(IShortTerm memory) {
        memory.updateShortTermMemory(foundEvent, 0);
        memory.updateShortTermMemory(followingEvent, 0);
        memory.updateShortTermMemory(lostEvent, 0);
    }

    @Override
    public Maybe<IAction<IConsciousness>> execute(IReadBody body, IReadSurroundings surroundings, IShortTerm memory) {
        if (foundPlayer(body, surroundings)) {
            memory.updateShortTermMemory(foundEvent, 1);
            return new Nothing<>();
        } else if (lostPlayer(body, surroundings)) {
            memory.updateShortTermMemory(lostEvent, 1);
            return new Nothing<>();
        } else {
            memory.updateShortTermMemory(followingEvent, 1);
            return surroundings.readPlayer().chain(p -> {
                Vector2f nextPosition = surroundings.getPath(body.readPosition(), p.readPosition()).b;
                return Maybe.wrap(moveTowards(body, nextPosition));
            });
        }
    }

    private boolean foundPlayer(IReadBody body, IReadSurroundings surroundings) {
        return surroundings.readPlayer().check(p -> Intelligence.isFacing(body, p.readPosition()));
    }

    private boolean lostPlayer(IReadBody body, IReadSurroundings surroundings) {
        return !(surroundings.getPath(body.readPosition(), body.readStartingPosition()).c < followingDistance)
                && !isInRange(body, surroundings);
    }

    private boolean isInRange(IReadBody body, IReadSurroundings surroundings) {
        return surroundings.readPlayer().check(player -> {
            int cost = surroundings.getPath(body.readPosition(), player.readPosition()).c;
            return cost > 0 && cost <= followingDistance;
        });
    }

    @Override
    public boolean equals(Object o){
        Follow other;

        if(!(o instanceof Follow)) {
            return false;
        }

        other = (Follow) o;

        return other.followingDistance == followingDistance &&
                other.foundEvent.equals(foundEvent) &&
                other.followingEvent.equals(followingEvent) &&
                other.lostEvent.equals(lostEvent) &&
                super.equals(o);
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}
