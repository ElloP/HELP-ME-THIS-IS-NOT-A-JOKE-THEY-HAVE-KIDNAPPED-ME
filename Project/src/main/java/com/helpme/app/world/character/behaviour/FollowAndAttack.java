package com.helpme.app.world.character.behaviour;


import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.consciousness.IReadSurroundings;

import java.util.List;
import java.util.Random;

/**
 * Created by kopa on 2017-04-14.
 */
public class FollowAndAttack implements IBehaviour{
    private int followingDistance;

    public FollowAndAttack(int maxDistance){
        Random random = new Random(System.currentTimeMillis());
        this.followingDistance = maxDistance;
    }

    public Maybe update(IReadBody body, IReadSurroundings surroundings) {
        return updateBehaviour(body, surroundings);
    }

    private Maybe updateBehaviour(IReadBody body, IReadSurroundings surroundings){
        System.out.println(followingDistance);
        if (decideToAttack(body, surroundings)){
            return new Just(Action.attackAction(surroundings));
        } else if (surroundings.getShortestPath(body.readPosition(), body.readStartingPosition()).c < followingDistance) {
            Maybe<IReadBody> maybePlayer = surroundings.readPlayer();
            if(maybePlayer.isJust()) {
                IReadBody player = maybePlayer.getValue();
                Tuple3<List<Vector2f>, Vector2f, Integer> path = surroundings.getShortestPath(body.readPosition(), player.readPosition());
                int cost = path.c;
                if (cost > 0 && cost <= followingDistance) {
                    Vector2f nextPos = path.b;
                    return new Just(Intelligence.moveOrRotateAction(body, nextPos));
                } else {
                    return new Just(new Left(new FollowAndAttack(followingDistance)));
                }
            } else {
                return new Just(new Left(new FollowAndAttack(followingDistance)));
            }
        } else {
            return new Just(new Left<>(new GoBack()));
        }
    }

    private boolean decideToFollow(IReadBody body, IReadSurroundings surroundings){
        Maybe<IReadBody> maybePlayer = surroundings.readPlayer();
        if(maybePlayer.isJust()) {
            IReadBody player = maybePlayer.getValue();
            Vector2f destination = player.readPosition();
            int longestDistance = followingDistance;
            if (Vector2f.equals(body.readStartingPosition(), destination))
                longestDistance--;
            return surroundings.isDistanceFrom(body, player.readPosition(), longestDistance);
        }
        return false;
    }

    private boolean decideToAttack(IReadBody body, IReadSurroundings surroundings){
        return surroundings.readPlayer().check(p -> Intelligence.isMonsterFacing(body, p.readPosition()));
    }
}
