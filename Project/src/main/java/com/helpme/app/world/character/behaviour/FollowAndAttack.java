package com.helpme.app.world.character.behaviour;


import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.level.read.IReadLevel;

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

    public FollowAndAttack(){
        this.followingDistance = 5;
    }

    @Override
    public Either update(IReadMonster monster, IReadLevel level) {
        return updateBehaviour(monster, level);
    }

    private Either updateBehaviour(IReadMonster monster, IReadLevel level){
        System.out.println(followingDistance);
        if (decideToAttack(monster, level)){
            return Action.attackAction(level);
        } else if (level.getShortestPath(monster.readPosition(), monster.readStartingPosition()).c < followingDistance) {
            Maybe<IReadMonster> maybePlayer = level.readPlayer();
            if(maybePlayer.isJust()) {
                IReadMonster player = maybePlayer.getValue();
                Vector2f pos = monster.readPosition();
                Tuple3<List<Vector2f>, Vector2f, Integer> path = level.getShortestPath(monster.readPosition(), player.readPosition());
                int cost = path.c;
                if (cost > 0 && cost <= followingDistance) {
                    Vector2f nextPos = path.b;
                    return Intelligence.moveOrRotateAction(monster, nextPos);
                } else {
                    return new Left(new FollowAndAttack(followingDistance));
                }
            } else {
                return new Left(new FollowAndAttack(followingDistance));
            }
        } else {
            return new Left<IBehaviour, IAction<IMonster>>(new GoBack());
        }
    }

    private boolean decideToFollow(IMonster monster, IReadLevel level){
        Maybe<IReadMonster> maybePlayer = level.readPlayer();
        if(maybePlayer.isJust()) {
            IReadMonster player = maybePlayer.getValue();
            Vector2f destination = player.readPosition();
            int longestDistance = followingDistance;
            if (Vector2f.equals(monster.readStartingPosition(), destination))
                longestDistance--;
            return level.isDistanceFrom(monster, player.readPosition(), longestDistance);
        }
        return false;
    }

    private boolean decideToAttack(IReadMonster monster, IReadLevel level){
        return level.readPlayer().check(p -> Intelligence.isMonsterFacing(monster, p.readPosition()));
    }
}
