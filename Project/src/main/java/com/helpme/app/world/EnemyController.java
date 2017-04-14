package com.helpme.app.world;

import com.helpme.app.character.IMonster;
import com.helpme.app.character.behaviour.DoNothing;
import com.helpme.app.character.behaviour.IBehaviour;
import com.helpme.app.utils.Vector2f;

/**
 * Created by Jesper on 2017-04-12.
 */
public class EnemyController extends MonsterController {
    private IBehaviour behaviour;


    public EnemyController(IMonster monster, ILevel level, IBehaviour behaviour){
        super(monster, level);
        this.behaviour = behaviour == null ? new DoNothing() : behaviour;
    }

    @Override
    public void update() {
        behaviour.update(monster, level);
    }

    public EnemyController(IMonster monster, ILevel level){
        super(monster, level);
    }

    private IMonster getPlayer(){
        return level.getPlayer();
    }
}
