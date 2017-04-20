package com.helpme.app;

import com.helpme.app.Mock.MockWorld1;
import com.helpme.app.character.IMonster;
import com.helpme.app.utils.Tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
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
    public void testNotAttack(){
        assert (mockWorld.enemyController0.getMonster().getHitpoints().equals(new Vector2f(100,100)));
        mockWorld.enemyController1.update();
        assert (mockWorld.enemyController1.getMonster().getHitpoints().equals(new Vector2f(100,100)));
    }

    @Test
    public void testFollow(){
        System.out.println(mockWorld.enemyController0.getMonster().getPosition());
        assert (mockWorld.enemyController0.getMonster().getPosition().equals(new Vector2f(1,0)));
        mockWorld.playerController.movePlayerForward();
        mockWorld.enemyController0.update();
        System.out.println(mockWorld.enemyController0.getMonster().getPosition());
        assert (mockWorld.enemyController0.getMonster().getPosition().equals(new Vector2f(0,0)));
    }

    @Test
    public void testSearchPath(){
        Vector2f playerPos = new Vector2f(0, 0);
        Vector2f monsterPos = mockWorld.enemyController1.getMonster().getPosition();
        assert monsterPos.equals(new Vector2f(0, 3));
        assert mockWorld.playerController.getPlayer().getPosition().equals(new Vector2f(0,0));
        Tuple3 path = mockWorld.level.getShortestPath(monsterPos, playerPos);
        assert (int) path.c == 4;
    }

    @Test
    public void testRotateMove(){
        assert mockWorld.playerController.getPlayer().getPosition().equals(new Vector2f(0,0));
        assert mockWorld.playerController.getPlayer().getDirection().equals(Vector2f.up);
        //mockWorld.playerController.rotatePlayerLeft();
        //mockWorld.playerController.movePlayerRight();
        //mockWorld.playerController.rotatePlayerRight();
        mockWorld.playerController.movePlayerForward();
        System.out.println(mockWorld.playerController.getPlayer().getPosition());
        //assert mockWorld.playerController.getPlayer().getPosition().equals(new Vector2f(1,0));

    }
}
