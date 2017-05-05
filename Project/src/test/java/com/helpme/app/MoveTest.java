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
        mockWorld.player.rotatePlayerRight();
        assert ( mockWorld.player.getPlayer().readDirection().equals(Vector2f.right));
         mockWorld.player.rotatePlayerRight();
        assert ( mockWorld.player.getPlayer().readDirection().equals(Vector2f.down));
         mockWorld.player.rotatePlayerRight();
        assert ( mockWorld.player.getPlayer().readDirection().equals(Vector2f.left));
         mockWorld.player.rotatePlayerRight();
        assert ( mockWorld.player.getPlayer().readDirection().equals(Vector2f.up));
    }

    @Test
    public void testRotateLeft() {
         mockWorld.player.rotatePlayerLeft();
        assert ( mockWorld.player.getPlayer().readDirection().equals(Vector2f.left));
         mockWorld.player.rotatePlayerLeft();
        assert ( mockWorld.player.getPlayer().readDirection().equals(Vector2f.down));
         mockWorld.player.rotatePlayerLeft();
        assert ( mockWorld.player.getPlayer().readDirection().equals(Vector2f.right));
         mockWorld.player.rotatePlayerLeft();
        assert ( mockWorld.player.getPlayer().readDirection().equals(Vector2f.up));
    }

    @Test
    public void testBlockedByMonster() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.movePlayerForward();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileStart));
    }

    @Test
    public void testWalkAroundMonster() {
        Vector2f tileStart = new Vector2f(2, 1);
        Vector2f tileTo = new Vector2f(2, 3);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.movePlayerRight();
        mockWorld.player.movePlayerForward();
        mockWorld.player.movePlayerForward();
        mockWorld.player.movePlayerLeft();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileTo));
    }

    @Test
    public void testWalkOverDeadBodies(){
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.movePlayerForward();
        Vector2f monsterPos = new Vector2f(Vector2f.add(tileStart,mockWorld.player.getPlayer().readDirection()));
        //assert (monsterPos.equals(new Vector2f(2,2)));
        while(!mockWorld.level.readMonster(monsterPos).check(m -> m.isDead())){
            mockWorld.player.usePlayerAttack();
        }
        mockWorld.player.movePlayerForward();
        assert (mockWorld.player.getPlayer().readPosition().equals(monsterPos));

    }
}
