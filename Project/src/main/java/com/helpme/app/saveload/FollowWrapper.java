package com.helpme.app.saveload;

import com.helpme.app.world.character.behaviour.Follow;

/**
 * Created by kopa on 2017-05-17.
 */
public class FollowWrapper implements ILoadable<Follow>{



    public FollowWrapper(){

    }

    public FollowWrapper(Follow followBehaviour){

    }

    @Override
    public Follow getObject() {
        return null;
    }
}
