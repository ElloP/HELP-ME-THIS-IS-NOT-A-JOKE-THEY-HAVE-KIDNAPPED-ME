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
public class BehaviourTest {
    private MockWorld1 mockWorld;

    @Before
    public void setup(){
        this.mockWorld = new MockWorld1();
    }

    @Test
    public void testAttack(){
        assert (mockWorld.enemyConsciousness0.readBody().readHitpoints().equals(new Vector2f(100,100)));
        mockWorld.enemyConsciousness0.update();
        assert (mockWorld.player.getPlayer().readHitpoints().equals(new Vector2f(100, 98)));
    }

    @Test
    public void testChangeBehavior(){
        assert ((Enemy) mockWorld.enemyConsciousness2).getBehaviour() instanceof FollowAndAttack;
        mockWorld.enemyConsciousness2.moveForward();
        mockWorld.enemyConsciousness2.moveForward();
        mockWorld.enemyConsciousness2.update();
        assert ((Enemy) mockWorld.enemyConsciousness2).getBehaviour() instanceof GoBack;
    }

    @Test
    public void testFollow(){
        assert mockWorld.enemyConsciousness1.readBody().readPosition().equals(new Vector2f(0, 3));
        assert mockWorld.level.readPlayer().check(p -> p.readPosition().equals(new Vector2f(0, 0)));
        assert mockWorld.enemyConsciousness1.readBody().readDirection().equals(Vector2f.down);
        mockWorld.enemyConsciousness1.update();
        assert mockWorld.enemyConsciousness1.readBody().readPosition().equals(new Vector2f(0, 2)); // NOTE (Jacob) : Random when it works. Why?
    }

    @Test
    public void testGoBack() {
        assert mockWorld.enemyConsciousness3.readBody().readStartingPosition().equals(mockWorld.enemyConsciousness3.readBody().readPosition());
        assert ((Enemy) mockWorld.enemyConsciousness3).getBehaviour() instanceof GoBack;
        mockWorld.enemyConsciousness3.update();
        assert ((Enemy) mockWorld.enemyConsciousness3).getBehaviour() instanceof FollowAndAttack;
    }

}
