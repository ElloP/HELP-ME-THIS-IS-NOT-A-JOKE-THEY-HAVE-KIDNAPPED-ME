package com.helpme.app.world.handler;
import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.either.Right;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IMonster;
import com.helpme.app.world.character.IReadMonster;
import com.helpme.app.world.character.behaviour.DoNothing;
import com.helpme.app.world.character.behaviour.IBehaviour;
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
        Either<IBehaviour, IAction<IMonster>> actionOrBehavior = behaviour.update(monster, level);
        if (actionOrBehavior instanceof Left){
            this.behaviour = (IBehaviour) ((Left) actionOrBehavior).getValue();
        } else {
            IAction<IMonster> action = (IAction<IMonster>) ((Right) actionOrBehavior).getValue();
            action.apply(monster);
        }
    }

    public EnemyHandler(IMonster monster, ILevel level){
        super(monster, level);
    }

    private Maybe<IReadMonster> getPlayer(){
        return level.getPlayer();
    }

    public IBehaviour getBehaviour(){
        return behaviour;
    }
}
