package com.helpme.app.world.character.behaviour;


import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.consciousness.ISurroundings;

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

    @Override
    public Either update(IReadBody monster, ISurroundings level) {
        return updateBehaviour(monster, level);
    }

    private Either updateBehaviour(IReadBody monster, ISurroundings level){

        //TODO (Jacob) Impossible to read this, man
        System.out.println(followingDistance);
        if (decideToAttack(monster, level)){
            return Action.attackAction(level);
        } else if (level.getShortestPath(monster.readPosition(), monster.readStartingPosition()).c < followingDistance) {
            Maybe<IReadBody> maybePlayer = level.readPlayer();
            if(maybePlayer.isJust()) {
                IReadBody player = maybePlayer.getValue();
                Tuple3<List<Vector2f>, Vector2f, Integer> path = level.getShortestPath(monster.readPosition(), player.readPosition());
                int cost = path.c;
                if (cost > 0 && cost <= followingDistance) {
                    Vector2f nextPos = path.b;
                    return Intelligence.moveOrRotateAction(monster, nextPos);
                } else {
                    return new Left(this);
                }
            } else {
                return new Left(this);
            }
        } else {
            return new Left<IBehaviour, IAction<IBody>>(new GoBack());
        }
    }

    private boolean decideToFollow(IReadBody monster, ISurroundings level){
        Maybe<IReadBody> maybePlayer = level.readPlayer();
        if(maybePlayer.isJust()) {
            IReadBody player = maybePlayer.getValue();
            Vector2f destination = player.readPosition();
            int longestDistance = followingDistance;
            if (Vector2f.equals(monster.readStartingPosition(), destination))
                longestDistance--;
            return level.isDistanceFrom(monster, player.readPosition(), longestDistance);
        }
        return false;
    }

    private boolean decideToAttack(IReadBody monster, ISurroundings level){
        return level.readPlayer().check(p -> Intelligence.isMonsterFacing(monster, p.readPosition()));
    }
}
