package com.helpme.app.character.behaviour;

import com.helpme.app.character.IMonster;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.ILevel;

/**
 * Created by kopa on 2017-04-14.
 */
public class AttackEveryoneClose implements IBehaviour{
    private int followingDistance;

    public AttackEveryoneClose(int followingDistance){
        this.followingDistance = followingDistance;
    }

    @Override
    public void update(IMonster monster, ILevel level) {
        updateBehaviour(monster, level);
    }

    private void updateBehaviour(IMonster monster, ILevel level){
        if (decideToAttack(monster, level))
            monster.attack(level.getPlayer());
        else if (level.isDistanceFrom(monster, monster.getStartingPosition(), followingDistance)){
            //TODO (Jesper): where to go
            if (decideToFollow(monster, level)){
                monster.moveForward();
            }
        }
        //TODO (Jesper): how to get back
        else{

        }
    }

    private boolean decideToFollow(IMonster monster, ILevel level){
        Vector2f destination = level.getPlayer().getPosition();
        int longestDistance = followingDistance;
        if (Vector2f.equals(monster.getStartingPosition(), destination))
            longestDistance--;
        return level.isDistanceFrom(monster, level.getPlayer().getPosition(), longestDistance);
    }

    private boolean decideToAttack(IMonster monster, ILevel level){
        return (Intelligence.isMonsterNextTo(monster, level.getPlayer(), level));
    }
}
