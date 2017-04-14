package com.helpme.app.character.behaviour;

import com.helpme.app.character.IMonster;
import com.helpme.app.world.ILevel;

/**
 * Created by kopa on 2017-04-14.
 */
public class AttackEveryoneClose implements IBehaviour{
    private boolean attackMode;

    @Override
    public void update(IMonster monster, ILevel level) {
        updateBehaviour(monster, level);
    }

    private void updateBehaviour(IMonster monster, ILevel level){
        attackMode = false;
        decideToAttack(monster, level);
        if (attackMode)
            monster.attack(level.getPlayer());
    }
    private void decideToAttack(IMonster monster, ILevel level){
        if (Intelligence.isNextTo(level.getPlayer(), level))
                attackMode = true;

    }
}
