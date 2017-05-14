package com.helpme.app.consciousnesstest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.IBody;
import com.helpme.app.world.character.behaviour.FollowAndAttack;
import com.helpme.app.world.character.behaviour.GoBack;
import com.helpme.app.world.consciousness.ConsciousnessFactory;
import com.helpme.app.world.consciousness.Enemy;
import com.helpme.app.world.consciousness.IThought;
import com.helpme.app.world.consciousness.IConsciousness;
import com.helpme.app.world.item.IItem;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-11.
 */
public class ConsciousnessTest {
    IThought enemy;
    IConsciousness player;
    IBody mockBody;
    IBody mockTarget;

    @Before
    public void setup() {
        mockTarget = new MockTarget();
        mockBody = new MockBody();
        player = ConsciousnessFactory.createPlayer(mockBody, new MockSurroundings(mockTarget, new IItem[]{}));
    }

    @Test
    public void testAttack(){
        player.useAttack();
        assert (mockTarget.readHitpoints().y == 90);
    }

    @Test
    public void testSelfie(){
        player.useSelfie();
        assert (mockBody.isDead());
    }

    @Test
    public void testPickupAll(){
        player.usePickupAll();
    }

    @Test
    public void testPickupSingle(){

    }



}
