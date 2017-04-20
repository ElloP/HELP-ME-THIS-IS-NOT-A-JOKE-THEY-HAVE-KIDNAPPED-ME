package com.helpme.app.world.character.behaviour;

import com.helpme.app.utils.maybe.Just;
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
    public void update(IMonster monster, IReadLevel level) {
        updateBehaviour(monster, level);
    }

    private void updateBehaviour(IMonster monster, IReadLevel level) {
        if (decideToAttack(monster, level)) {
            Maybe<ITarget> maybeTarget = level.getTarget(monster, monster.getDirection());
            if (maybeTarget.isJust()) {
                monster.attack(maybeTarget.getValue());
            }

        } else if (level.isDistanceFrom(monster, monster.getStartingPosition(), followingDistance)) {
            //TODO (Jesper): where to go
            if (decideToFollow(monster, level)) {
                monster.moveForward();
            }
        }
        //TODO (Jesper): how to get back
        else {

        }
    }

    private boolean decideToFollow(IMonster monster, IReadLevel level) {
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

    private boolean decideToAttack(IMonster monster, IReadLevel level) {
        Maybe<IReadMonster> maybePlayer = level.getPlayer();
        return (maybePlayer.check(p -> Intelligence.isMonsterNextTo(monster, p, level)));
    }
}
