package com.helpme.app;

import com.helpme.app.Mock.MockWorld;
import com.helpme.app.utils.Vector2f;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jesper on 2017-04-14.
 */
public class MonsterTest {
    private MockWorld mockWorld;

    @Before
    public void setup(){
        this.mockWorld = new MockWorld();
    }

    @Test
    public void testAttack(){
        assert (mockWorld.monster0Controller.getPlayer().getHitpoints().equals(new Vector2f(100,100)));
        mockWorld.monster0Controller.update();
        assert (mockWorld.monster0Controller.getPlayer().getHitpoints().equals(new Vector2f(100, 98)));
    }

    @Test
    public void testNotAttack(){
        assert (mockWorld.monster0Controller.getPlayer().getHitpoints().equals(new Vector2f(100,100)));
        mockWorld.monster1Controller.update();
        assert (mockWorld.monster1Controller.getPlayer().getHitpoints().equals(new Vector2f(100,100)));
    }

    @Test
    public void testFollow(){
        assert (mockWorld.monster0Controller.getMonster().getPosition().equals(new Vector2f(2,2)));
        mockWorld.concretePlayerController.movePlayerBackward();
        assert (mockWorld.monster0Controller.getMonster().getPosition().equals(new Vector2f(2,1)));
    }
}
