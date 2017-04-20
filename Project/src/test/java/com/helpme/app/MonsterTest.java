package com.helpme.app;

import com.helpme.app.Mock.MockWorld1;
import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.behaviour.FollowAndAttack;
import com.helpme.app.world.character.behaviour.GoBack;
import com.helpme.app.world.handler.EnemyHandler;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jesper on 2017-04-14.
 */
public class MonsterTest {
    private MockWorld1 mockWorld;

    @Before
    public void setup(){
        this.mockWorld = new MockWorld1();
    }

    @Test
    public void testAttack(){
        assert (mockWorld.enemyController0.getMonster().getHitpoints().equals(new Vector2f(100,100)));
        mockWorld.enemyController0.update();
        assert (mockWorld.playerController.getPlayer().getHitpoints().equals(new Vector2f(100, 98)));
    }

    @Test
    public void testChangeBehavior(){
        assert ((EnemyHandler) mockWorld.enemyController2).getBehaviour() instanceof FollowAndAttack;
        mockWorld.enemyController2.moveMonsterForward();
        mockWorld.enemyController2.moveMonsterForward();
        mockWorld.enemyController2.update();
        assert ((EnemyHandler) mockWorld.enemyController2).getBehaviour() instanceof GoBack;
    }

    //Note (Jesper): if it fails, try again. The argument to the FollowAndAttack constructor is now just max distance.
    //The actual followingDistance will be random.
    @Test
    public void testFollow(){
        assert mockWorld.enemyController1.getMonster().getPosition().equals(new Vector2f(0, 3));
        assert mockWorld.level.getPlayer().getPosition().equals(new Vector2f(0, 0));
        assert mockWorld.enemyController1.getMonster().getDirection().equals(Vector2f.down);
        mockWorld.enemyController1.update();
        assert mockWorld.enemyController1.getMonster().getPosition().equals(new Vector2f(0, 2));
    }




}
