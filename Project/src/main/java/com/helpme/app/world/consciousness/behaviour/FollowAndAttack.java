package com.helpme.app.world.consciousness.behaviour;


import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Nothing;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.IReadSurroundings;

import java.util.List;
import java.util.Random;

/**
 * Created by kopa on 2017-04-14.
 */
public class FollowAndAttack implements IBehaviour{
    private int followingDistance;
    private IReadBody body;
    private Tuple3<List<Vector2f>, Vector2f, Integer> path;

    public FollowAndAttack(int maxDistance){
        Random random = new Random(System.currentTimeMillis());
        this.followingDistance = random.nextInt(maxDistance);
    }

    public FollowAndAttack() {
        this.followingDistance = 8;
    }

    public Maybe update(IReadBody body, IReadSurroundings surroundings) {
        return updateBehaviour(body, surroundings);
    }

    private Maybe updateBehaviour(IReadBody body, IReadSurroundings surroundings){
        this.body = body;
        if (decideToAttack(body, surroundings)){
            return new Just(Action.attackAction(surroundings));
        } else if (closeToStartPos(surroundings)) {
            Maybe<IReadBody> maybePlayer = surroundings.readPlayer();
            if(maybePlayer.isJust()) {
                if (decideToFollow(maybePlayer, surroundings)) {
                    return new Just(Intelligence.moveOrRotateAction(body, getNextPos()));
                } else {
                    return new Just(new Left(new FollowAndAttack(followingDistance)));
                }
            } else {
                return new Nothing();
            }
        } else {
            return new Just(new Left<>(new GoBack()));
        }
    }

    private boolean closeToStartPos(IReadSurroundings surroundings) {
        return surroundings.getShortestPath(body.readPosition(), body.readStartingPosition()).c < followingDistance;
    }

    private boolean decideToFollow(Maybe<IReadBody> maybePlayer, IReadSurroundings surroundings) {
        IReadBody player = maybePlayer.getValue();
        path = surroundings.getShortestPath(body.readPosition(), player.readPosition());
        return withinFollowDistance();
    }

    private Vector2f getNextPos() {
        return path.b;
    }

    private boolean withinFollowDistance() {
        int cost = path.c;
        return cost > 0 && cost <= followingDistance;
    }

    private boolean decideToAttack(IReadBody body, IReadSurroundings surroundings){
        return surroundings.readPlayer().check(p -> Intelligence.isMonsterFacing(body, p.readPosition()));
    }
}
