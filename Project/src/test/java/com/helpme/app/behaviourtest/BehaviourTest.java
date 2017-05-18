package com.helpme.app.behaviourtest;

import com.helpme.app.world.character.behaviour.Stay;
import com.helpme.app.world.character.behaviour.Return;
import com.helpme.app.world.character.behaviour.IBehaviour;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-15.
 */
public class BehaviourTest {
    IBehaviour doNothing;
    IBehaviour followAndAttack;
    IBehaviour goBack;

    Mock

    @Before
    public void setup(){
        doNothing = new Stay();
        followAndAttack = new FollowAndAttack();
        goBack = new Return();
    }

    @Test
    public void testFollowAndAttack(){
        followAndAttack.update()
    }

    @Test
    public void testGoBack(){

    }

    @Test
    public void testDoNothing(){

    }


}
