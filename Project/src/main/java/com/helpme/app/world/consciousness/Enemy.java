package com.helpme.app.world.consciousness;
import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.either.Right;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.behaviour.DoNothing;
import com.helpme.app.world.character.behaviour.IBehaviour;
import com.helpme.app.world.level.ILevel;

/**
 * Created by Jesper on 2017-04-12.
 */
public class Enemy extends Consciousness {
    private IBehaviour behaviour;

    public Enemy(IBody monster, ILevel level, IBehaviour behaviour){
        super(monster, level);
        this.behaviour = behaviour == null ? new DoNothing() : behaviour;
    }

    @Override
    public void update() {
        Either<IBehaviour, IAction<IBody>> actionOrBehavior = behaviour.update(monster, surroundings);
        if (actionOrBehavior instanceof Left){
            this.behaviour = (IBehaviour) ((Left) actionOrBehavior).getValue();
        } else {
            IAction<IBody> action = (IAction<IBody>) ((Right) actionOrBehavior).getValue();
            action.apply(monster);
        }
    }

    public Enemy(IBody monster, ILevel level){
        super(monster, level);
    }

    private Maybe<IReadBody> getPlayer(){
        return surroundings.readPlayer();
    }

    public IBehaviour getBehaviour(){
        return behaviour;
    }
}
