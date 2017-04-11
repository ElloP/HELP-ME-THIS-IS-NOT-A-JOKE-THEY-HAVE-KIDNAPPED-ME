package com.helpme.app;

import com.helpme.app.Mock.MockWorld;
import com.helpme.app.utils.Vector2f;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jacob on 2017-04-11.
 */
public class EdgeTest {
    private MockWorld mockWorld;

    @Before
    public void setUp() {
        mockWorld = new MockWorld();
    }

    @Test
    public void testWalkThroughUnlockedDoor() {
        Vector2f tileStart = new Vector2f(7, 2);
        Vector2f tileTo = new Vector2f(8, 2);
         mockWorld.playerController.setPlayerPosition(tileStart);
         mockWorld.playerController.rotatePlayerRight();
         mockWorld.playerController.movePlayerForward();
        assert ( mockWorld.playerController.getPlayer().getPosition().equals(tileTo));
    }

    @Test
    public void testBlockedByLockedDoor() {
        Vector2f tileStart = new Vector2f(7, 2);
         mockWorld.playerController.setPlayerPosition(tileStart);
         mockWorld.playerController.rotatePlayerLeft();
         mockWorld.playerController.movePlayerForward();
        assert ( mockWorld.playerController.getPlayer().getPosition().equals(tileStart));
    }

    @Test
    public void testUnlockDoorAndWalkThrough() {
        Vector2f tileStart = new Vector2f(8, 2);
        Vector2f tileTo = new Vector2f(9, 2);
         mockWorld.playerController.setPlayerPosition(tileStart);
         mockWorld.playerController.rotatePlayerRight();
         mockWorld.playerController.movePlayerForward();
         mockWorld.playerController.movePlayerForward();
        assert ( mockWorld.playerController.getPlayer().getPosition().equals(tileTo));
    }

    @Test
    public void testBlockedByWall() {
        Vector2f tileSingle = new Vector2f(5, 5);
        mockWorld.playerController.setPlayerPosition(tileSingle);
        mockWorld.playerController.movePlayerForward();
        assert ( mockWorld.playerController.getPlayer().getPosition().equals(tileSingle));
        mockWorld.playerController.movePlayerRight();
        assert ( mockWorld.playerController.getPlayer().getPosition().equals(tileSingle));
        mockWorld.playerController.movePlayerBackward();
        assert ( mockWorld.playerController.getPlayer().getPosition().equals(tileSingle));
        mockWorld.playerController.movePlayerLeft();
        assert ( mockWorld.playerController.getPlayer().getPosition().equals(tileSingle));
    }

    @Test
    public void testMoveThroughOpening() {
        Vector2f tileTo = new Vector2f(2, 1);

        mockWorld.playerController.rotatePlayerRight();
        mockWorld.playerController.movePlayerForward();
        mockWorld.playerController.movePlayerForward();
        mockWorld.playerController.rotatePlayerLeft();
        mockWorld.playerController.movePlayerForward();
        assert ( mockWorld.playerController.getPlayer().getPosition().equals(tileTo));
    }
}
