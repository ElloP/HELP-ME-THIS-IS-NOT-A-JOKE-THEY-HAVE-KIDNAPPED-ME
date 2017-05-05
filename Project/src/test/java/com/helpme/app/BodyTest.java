package com.helpme.app;

import com.helpme.app.Mock.MockWorld1;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.character.behaviour.FollowAndAttack;
import com.helpme.app.world.character.behaviour.GoBack;
import com.helpme.app.world.consciousness.Enemy;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jesper on 2017-04-14.
 */
public class BodyTest {
    private MockWorld1 mockWorld;

    @Before
    public void setup(){
        this.mockWorld = new MockWorld1();
    }

    @Test
    public void testAttack(){
        assert (mockWorld.enemyHandler0.readMonster().readHitpoints().equals(new Vector2f(100,100)));
        mockWorld.enemyHandler0.update();
        assert (mockWorld.player.getPlayer().readHitpoints().equals(new Vector2f(100, 98)));
    }

    @Test
    public void testChangeBehavior(){
        assert ((Enemy) mockWorld.enemyHandler2).getBehaviour() instanceof FollowAndAttack;
        mockWorld.enemyHandler2.moveMonsterForward();
        mockWorld.enemyHandler2.moveMonsterForward();
        mockWorld.enemyHandler2.update();
        System.out.println(((Enemy)mockWorld.enemyHandler2).getBehaviour().getClass().toString());
        assert ((Enemy) mockWorld.enemyHandler2).getBehaviour() instanceof GoBack;
    }

    @Test
    public void testFollow(){
        assert mockWorld.enemyHandler1.readMonster().readPosition().equals(new Vector2f(0, 3));
        assert mockWorld.level.readPlayer().check(p -> p.readPosition().equals(new Vector2f(0, 0)));
        assert mockWorld.enemyHandler1.readMonster().readDirection().equals(Vector2f.down);
        mockWorld.enemyHandler1.update();

        assert mockWorld.enemyHandler1.readMonster().readPosition().equals(new Vector2f(0, 2)); // NOTE (Jacob) : Random when it works. Why?
    }

}
