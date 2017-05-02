package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.character.ITarget;
import com.helpme.app.world.level.IReadLevel;

/**
 * Created by kopa on 2017-04-14.
 */
public class AttackEveryoneClose implements IBehaviour {
    private int followingDistance;

    public AttackEveryoneClose(int followingDistance) {
        this.followingDistance = followingDistance;
    }

    @Override
    public Either<IBehaviour, IAction<IMonster>> update(IReadMonster monster, IReadLevel level) {
        return updateBehaviour(monster, level);
    }

    private Either<IBehaviour, IAction<IMonster>> updateBehaviour(IReadMonster monster, IReadLevel level) {
        if (decideToAttack(monster, level)) {
            return Action.attackAction(level);
        } else if (level.isDistanceFrom(monster, monster.getStartingPosition(), followingDistance)) {
            if (decideToFollow(monster, level)) {
                return Action.moveForwardAction();
            }
        }
        return new Left<>(this);
    }

    private boolean decideToFollow(IReadMonster monster, IReadLevel level) {
        Maybe<IReadMonster> maybePlayer = level.getPlayer();

        if (maybePlayer.isJust()) {
            IReadMonster player = maybePlayer.getValue();
            Vector2f destination = player.getPosition();

            int longestDistance = followingDistance;
            if (Vector2f.equals(monster.getStartingPosition(), destination))
                longestDistance--;
            return level.isDistanceFrom(monster, player.getPosition(), longestDistance);
        }
        return false;
    }

    private boolean decideToAttack(IReadMonster monster, IReadLevel level) {
        Maybe<IReadMonster> maybePlayer = level.getPlayer();
        return (maybePlayer.check(p -> Intelligence.isMonsterNextTo(monster, p, level)));
    }
}
