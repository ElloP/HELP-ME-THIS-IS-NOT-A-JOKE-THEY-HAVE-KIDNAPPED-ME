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
         mockWorld.playerHandler.setPlayerPosition(tileStart);
         mockWorld.playerHandler.rotatePlayerRight();
         mockWorld.playerHandler.movePlayerForward();
        assert ( mockWorld.playerHandler.getPlayer().readPosition().equals(tileTo));
    }

    @Test
    public void testBlockedByLockedDoor() {
        Vector2f tileStart = new Vector2f(7, 2);
         mockWorld.playerHandler.setPlayerPosition(tileStart);
         mockWorld.playerHandler.rotatePlayerLeft();
         mockWorld.playerHandler.movePlayerForward();
        assert ( mockWorld.playerHandler.getPlayer().readPosition().equals(tileStart));
    }

    @Test
    public void testUnlockDoorAndWalkThrough() {
        Vector2f tileStart = new Vector2f(8, 2);
        Vector2f tileTo = new Vector2f(9, 2);
         mockWorld.playerHandler.setPlayerPosition(tileStart);
         mockWorld.playerHandler.rotatePlayerRight();
         mockWorld.playerHandler.movePlayerForward();
         mockWorld.playerHandler.movePlayerForward();
        assert ( mockWorld.playerHandler.getPlayer().readPosition().equals(tileTo));
    }

    @Test
    public void testBlockedByWall() {
        Vector2f tileSingle = new Vector2f(5, 5);
        mockWorld.playerHandler.setPlayerPosition(tileSingle);
        mockWorld.playerHandler.movePlayerForward();
        assert ( mockWorld.playerHandler.getPlayer().readPosition().equals(tileSingle));
        mockWorld.playerHandler.movePlayerRight();
        assert ( mockWorld.playerHandler.getPlayer().readPosition().equals(tileSingle));
        mockWorld.playerHandler.movePlayerBackward();
        assert ( mockWorld.playerHandler.getPlayer().readPosition().equals(tileSingle));
        mockWorld.playerHandler.movePlayerLeft();
        assert ( mockWorld.playerHandler.getPlayer().readPosition().equals(tileSingle));
    }

    @Test
    public void testMoveThroughOpening() {
        Vector2f tileTo = new Vector2f(2, 1);

        mockWorld.playerHandler.rotatePlayerRight();
        mockWorld.playerHandler.movePlayerForward();
        mockWorld.playerHandler.movePlayerForward();
        mockWorld.playerHandler.rotatePlayerLeft();
        mockWorld.playerHandler.movePlayerForward();
        assert ( mockWorld.playerHandler.getPlayer().readPosition().equals(tileTo));
    }
}
