package com.helpme.app.world.consciousness.behaviour.concrete;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.IReadSurroundings;
import com.helpme.app.world.consciousness.behaviour.memories.IShortTerm;

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
    public Maybe<String> execute(IReadBody body, IReadSurroundings surroundings, IShortTerm memory) {
        if (foundPlayer(body, surroundings)) {
            memory.updateShortTermMemory(foundEvent, 1);
            return new Nothing<>();
        } else if (lostPlayer(body, surroundings)) {
            memory.updateShortTermMemory(followingEvent, 1);
            return surroundings.readPlayer().chain(p -> {
                Vector2f nextPosition = surroundings.getShortestPath(body.readPosition(), p.readPosition()).b;
                return moveTowards(p, nextPosition);
            });
        } else {
            memory.updateShortTermMemory(lostEvent, 1);
            return new Nothing<>();
        }
    }

    private boolean foundPlayer(IReadBody body, IReadSurroundings surroundings) {
        return surroundings.readPlayer().check(p -> Intelligence.isFacing(body, p.readPosition()));
    }

    private boolean lostPlayer(IReadBody body, IReadSurroundings surroundings) {
        return !(surroundings.getShortestPath(body.readPosition(), body.readStartingPosition()).c < followingDistance)
                && !isInRange(body, surroundings);
    }

    private boolean isInRange(IReadBody body, IReadSurroundings surroundings) {
        return surroundings.readPlayer().check(player -> {
            int cost = surroundings.getShortestPath(body.readPosition(), player.readPosition()).c;
            return cost > 0 && cost <= followingDistance;
        });
    }
}
