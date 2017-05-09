package com.helpme.app;

import com.helpme.app.Mock.MockWorld0;
import com.helpme.app.utils.Vector2f;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jacob on 2017-04-11.
 */
public class EdgeTest {
    private MockWorld0 mockWorld;

    @Before
    public void setUp() {
        mockWorld = new MockWorld0();
    }

    @Test
    public void testWalkThroughUnlockedDoor() {
        Vector2f tileStart = new Vector2f(7, 2);
        Vector2f tileTo = new Vector2f(8, 2);
         mockWorld.player.setPlayerPosition(tileStart);
         mockWorld.player.rotatePlayerRight();
         mockWorld.player.movePlayerForward();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileTo));
    }

    @Test
    public void testBlockedByLockedDoor() {
        Vector2f tileStart = new Vector2f(7, 2);
         mockWorld.player.setPlayerPosition(tileStart);
         mockWorld.player.rotatePlayerLeft();
         mockWorld.player.movePlayerForward();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileStart));
    }

    @Test
    public void testUnlockDoorAndWalkThrough() {
        Vector2f tileStart = new Vector2f(8, 2);
        Vector2f tileTo = new Vector2f(9, 2);
         mockWorld.player.setPlayerPosition(tileStart);
         mockWorld.player.rotatePlayerRight();
         mockWorld.player.movePlayerForward();
         mockWorld.player.movePlayerForward();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileTo));
    }

    @Test
    public void testBlockedByWall() {
        Vector2f tileSingle = new Vector2f(5, 5);
        mockWorld.player.setPlayerPosition(tileSingle);
        mockWorld.player.movePlayerForward();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileSingle));
        mockWorld.player.movePlayerRight();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileSingle));
        mockWorld.player.movePlayerBackward();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileSingle));
        mockWorld.player.movePlayerLeft();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileSingle));
    }

    @Test
    public void testMoveThroughOpening() {
        Vector2f tileTo = new Vector2f(2, 1);

        mockWorld.player.rotatePlayerRight();
        mockWorld.player.movePlayerForward();
        mockWorld.player.movePlayerForward();
        mockWorld.player.rotatePlayerLeft();
        mockWorld.player.movePlayerForward();
        assert ( mockWorld.player.getPlayer().readPosition().equals(tileTo));
    }
}
