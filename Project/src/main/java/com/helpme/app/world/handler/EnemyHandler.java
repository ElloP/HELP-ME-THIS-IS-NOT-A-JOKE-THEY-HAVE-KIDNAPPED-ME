package com.helpme.app.world.handler;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.IReadMonster;
import com.helpme.app.character.behaviour.DoNothing;
import com.helpme.app.character.behaviour.IBehaviour;
import com.helpme.app.world.level.ILevel;

/**
 * Created by Jesper on 2017-04-12.
 */
public class EnemyHandler extends MonsterHandler {
    private IBehaviour behaviour;


    public EnemyHandler(IMonster monster, ILevel level, IBehaviour behaviour){
        super(monster, level);
        this.behaviour = behaviour == null ? new DoNothing() : behaviour;
    }

    @Override
    public void update() {
        behaviour.update(monster, level);
    }

    public EnemyHandler(IMonster monster, ILevel level){
        super(monster, level);
    }

    private IReadMonster getPlayer(){
        return level.getPlayer();
    }
}
