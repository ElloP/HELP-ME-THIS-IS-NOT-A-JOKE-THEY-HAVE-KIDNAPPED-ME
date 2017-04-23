package com.helpme.app;

import com.helpme.app.Mock.MockWorld0;
import com.helpme.app.utils.Vector2f;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jacob on 2017-04-11.
 */
public class MoveTest {
    private MockWorld0 mockWorld;

    @Before
    public void setUp() {
        mockWorld = new MockWorld0();
    }

    @Test
    public void testRotateRight() {
        mockWorld.playerHandler.rotatePlayerRight();
        assert ( mockWorld.playerHandler.getPlayer().readDirection().equals(Vector2f.right));
         mockWorld.playerHandler.rotatePlayerRight();
        assert ( mockWorld.playerHandler.getPlayer().readDirection().equals(Vector2f.down));
         mockWorld.playerHandler.rotatePlayerRight();
        assert ( mockWorld.playerHandler.getPlayer().readDirection().equals(Vector2f.left));
         mockWorld.playerHandler.rotatePlayerRight();
        assert ( mockWorld.playerHandler.getPlayer().readDirection().equals(Vector2f.up));
    }

    @Test
    public void testRotateLeft() {
         mockWorld.playerHandler.rotatePlayerLeft();
        assert ( mockWorld.playerHandler.getPlayer().readDirection().equals(Vector2f.left));
         mockWorld.playerHandler.rotatePlayerLeft();
        assert ( mockWorld.playerHandler.getPlayer().readDirection().equals(Vector2f.down));
         mockWorld.playerHandler.rotatePlayerLeft();
        assert ( mockWorld.playerHandler.getPlayer().readDirection().equals(Vector2f.right));
         mockWorld.playerHandler.rotatePlayerLeft();
        assert ( mockWorld.playerHandler.getPlayer().readDirection().equals(Vector2f.up));
    }

    @Test
    public void testBlockedByMonster() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.playerHandler.setPlayerPosition(tileStart);
        mockWorld.playerHandler.movePlayerForward();
        assert ( mockWorld.playerHandler.getPlayer().readPosition().equals(tileStart));
    }

    @Test
    public void testWalkAroundMonster() {
        Vector2f tileStart = new Vector2f(2, 1);
        Vector2f tileTo = new Vector2f(2, 3);
        mockWorld.playerHandler.setPlayerPosition(tileStart);
        mockWorld.playerHandler.movePlayerRight();
        mockWorld.playerHandler.movePlayerForward();
        mockWorld.playerHandler.movePlayerForward();
        mockWorld.playerHandler.movePlayerLeft();
        assert ( mockWorld.playerHandler.getPlayer().readPosition().equals(tileTo));
    }

    @Test
    public void testWalkOverDeadBodies(){
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.playerHandler.setPlayerPosition(tileStart);
        mockWorld.playerHandler.movePlayerForward();
        Vector2f monsterPos = new Vector2f(Vector2f.add(tileStart,mockWorld.playerHandler.getPlayer().readDirection()));
        //assert (monsterPos.equals(new Vector2f(2,2)));
        while(!mockWorld.level.readMonster(monsterPos).check(m -> m.isDead())){
            mockWorld.playerHandler.usePlayerAttack();
        }
        mockWorld.playerHandler.movePlayerForward();
        assert (mockWorld.playerHandler.getPlayer().readPosition().equals(monsterPos));

    }
}
