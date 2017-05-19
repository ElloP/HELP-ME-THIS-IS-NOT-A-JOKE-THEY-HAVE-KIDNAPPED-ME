package com.helpme.app.world.consciousness.concrete;
import com.helpme.app.utils.either.Either;
import com.helpme.app.utils.either.Left;
import com.helpme.app.utils.either.Right;
import com.helpme.app.utils.functions.IAction;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.body.IBody;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.consciousness.ISurroundings;
import com.helpme.app.world.consciousness.behaviour.DoNothing;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;

/**
 * Created by Jesper on 2017-04-12.
 */
public class Enemy extends Consciousness {
    private IBehaviour behaviour;
    private IBehaviour defaultBehavior;

    public Enemy(IBody body, ISurroundings surroundings, IBehaviour behaviour, IBehaviour defaultBehavior){
        super(body, surroundings);
        this.behaviour = behaviour == null ? new DoNothing() : behaviour;
        this.defaultBehavior = defaultBehavior;
    }

    @Override
    public void update() {
        Maybe<Either<IBehaviour, IAction<IBody>>> nothingOrJust = behaviour.update(body, surroundings);
        if (nothingOrJust.isJust()) {
            Either<IBehaviour, IAction<IBody>> actionOrBehavior = nothingOrJust.getValue();
            if (actionOrBehavior instanceof Left){
                this.behaviour = (IBehaviour) ((Left) actionOrBehavior).getValue();
            } else {
                IAction<IBody> action = (IAction<IBody>) ((Right) actionOrBehavior).getValue();
                action.apply(body);
            }
        }
        else {
            behaviour = defaultBehavior;
        }
    }


    private Maybe<IReadBody> getPlayer(){
        return surroundings.readPlayer();
    }

    public IBehaviour getBehaviour(){
        return this.behaviour;
    }

    public IBehaviour getDefaultBehavior() {
        return this.defaultBehavior;
    }
}
