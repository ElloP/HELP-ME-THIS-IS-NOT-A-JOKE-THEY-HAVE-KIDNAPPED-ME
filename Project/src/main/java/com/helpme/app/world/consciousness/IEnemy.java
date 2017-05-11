package com.helpme.app.world.consciousness;

import com.helpme.app.world.character.behaviour.IBehaviour;

/**
 * Created by og on 2017-05-11.
 */
public interface IEnemy {
    IBehaviour getBehaviour();
    IBehaviour getDefaultBehavior();
}
