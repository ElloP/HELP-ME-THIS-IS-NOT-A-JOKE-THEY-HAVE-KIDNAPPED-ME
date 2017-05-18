package com.helpme.app.world.consciousness.concrete;

import com.helpme.app.world.body.IBody;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.consciousness.ISurroundings;
import com.helpme.app.world.consciousness.IThought;
import com.helpme.app.world.consciousness.behaviour.IBehaviour;

/**
 * Created by kopa on 2017-05-14.
 */
public final class ConsciousnessFactory {
    private ConsciousnessFactory(){

    }

    public static IThought createEnemy(IBody body, ISurroundings surroundings, IBehaviour behaviour, IBehaviour defaultBehaviour){
        return new Enemy(body, surroundings, behaviour, defaultBehaviour);
    }

    public static IConsciousness createPlayer(IBody body, ISurroundings surroundings){
        return new Player(body, surroundings);
    }

}
