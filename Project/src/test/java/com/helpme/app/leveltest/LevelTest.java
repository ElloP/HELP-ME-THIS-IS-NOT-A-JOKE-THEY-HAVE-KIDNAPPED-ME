package com.helpme.app.leveltest;

import com.helpme.app.Mock.MockWorld1;
import com.helpme.app.utils.tuple.Tuple3;
import com.helpme.app.utils.Vector2f;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Jesper on 2017-04-20.
 */
public class LevelTest {
    private MockWorld1 mockWorld;

    @Before
    public void setup(){
        this.mockWorld = new MockWorld1();
    }

//    @Test
//    public void testSearchPath(){
//        Vector2f playerPos = new Vector2f(0, 0);
//        Vector2f enemyPos = mockWorld.enemyConsciousness1.readBody().readPosition();
//        assert enemyPos.equals(new Vector2f(0, 3));
//        assert mockWorld.player.getPlayer().readPosition().equals(playerPos);
//        Tuple3 path = mockWorld.level.getShortestPath(enemyPos, playerPos);
//        assert (int) path.c == 4;
//        assert path.b.equals(new Vector2f(0, 2));
//        ArrayList<Vector2f> positions = new ArrayList<>();
//        positions.add(enemyPos);
//        positions.add(new Vector2f(0, 2));
//        positions.add(new Vector2f(0, 1));
//        positions.add(playerPos);
//        for (int i = 0; i < positions.size(); i++){
//            assert positions.get(i).equals(((ArrayList<Vector2f>) path.a).get(i));
//        }
//    }
}
