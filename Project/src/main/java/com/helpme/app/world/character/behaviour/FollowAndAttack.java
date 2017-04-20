package com.helpme.app.world.character.behaviour;


import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.level.IReadLevel;

import java.util.List;
import java.util.Random;

/**
 * Created by kopa on 2017-04-14.
 */
public class FollowAndAttack implements IBehaviour{
    private int followingDistance;

    public FollowAndAttack(int maxDistance){
        Random random = new Random(System.currentTimeMillis());
        this.followingDistance = random.nextInt(maxDistance);
    }

    @Override
    public Either update(IReadMonster monster, IReadLevel level) {
        return updateBehaviour(monster, level);
    }

    private Either updateBehaviour(IReadMonster monster, IReadLevel level){
        System.out.println(followingDistance);
        if (decideToAttack(monster, level)){
            return Action.attackAction(level);
        } else if (level.getShortestPath(monster.getPosition(), monster.getStartingPosition()).c < followingDistance) {
            Maybe<IReadMonster> maybePlayer = level.getPlayer();
            if(maybePlayer.isJust()) {
                IReadMonster player = maybePlayer.getValue();
                Vector2f pos = monster.getPosition();
                Tuple3<List<Vector2f>, Vector2f, Integer> path = level.getShortestPath(monster.getPosition(), player.getPosition());
                int cost = path.c;
                if (cost > 0 && cost <= followingDistance) {
                    Vector2f nextPos = path.b;
                    if (Intelligence.isMonsterFacing(monster, nextPos)) {
                        return Action.moveForwardAction();
                    } else if (Intelligence.isLeftOf(monster, nextPos)) {
                        return Action.rotateRight();
                    } else {
                        return Action.rotateLeft();
                    }
                }
            }
        } else {
            return new Left<IBehaviour, IAction<IMonster>>(new GoBack());
        }
        return new Left(null);
    }

    private boolean decideToFollow(IMonster monster, IReadLevel level){
        Maybe<IReadMonster> maybePlayer = level.getPlayer();
        if(maybePlayer.isJust()) {
            IReadMonster player = maybePlayer.getValue();
            Vector2f destination = player.getPosition();
            int longestDistance = followingDistance;
            if (Vector2f.equals(monster.getStartingPosition(), destination))
                longestDistance--;
            return level.isDistanceFrom(monster, player.getPosition(), longestDistance);
        }
        return false;
    }

    private boolean decideToAttack(IReadMonster monster, IReadLevel level){
        return level.getPlayer().check(p -> Intelligence.isMonsterFacing(monster, p.getPosition()));
    }
}
