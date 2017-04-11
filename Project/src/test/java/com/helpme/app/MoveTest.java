package com.helpme.app;

import com.helpme.app.Mock.MockWorld;
import com.helpme.app.utils.Vector2f;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-04-11.
 */
public class MoveTest {
    private MockWorld mockWorld;

    @Before
    public void setUp() {
        mockWorld = new MockWorld();
    }

    @Test
    public void testRotateRight() {
        mockWorld.playerController.rotatePlayerRight();
        assert ( mockWorld.playerController.getPlayer().getDirection().equals(Vector2f.right));
         mockWorld.playerController.rotatePlayerRight();
        assert ( mockWorld.playerController.getPlayer().getDirection().equals(Vector2f.down));
         mockWorld.playerController.rotatePlayerRight();
        assert ( mockWorld.playerController.getPlayer().getDirection().equals(Vector2f.left));
         mockWorld.playerController.rotatePlayerRight();
        assert ( mockWorld.playerController.getPlayer().getDirection().equals(Vector2f.up));
    }

    @Test
    public void testRotateLeft() {
         mockWorld.playerController.rotatePlayerLeft();
        assert ( mockWorld.playerController.getPlayer().getDirection().equals(Vector2f.left));
         mockWorld.playerController.rotatePlayerLeft();
        assert ( mockWorld.playerController.getPlayer().getDirection().equals(Vector2f.down));
         mockWorld.playerController.rotatePlayerLeft();
        assert ( mockWorld.playerController.getPlayer().getDirection().equals(Vector2f.right));
         mockWorld.playerController.rotatePlayerLeft();
        assert ( mockWorld.playerController.getPlayer().getDirection().equals(Vector2f.up));
    }

    @Test
    public void testBlockedByMonster() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.movePlayerForward();
        assert ( mockWorld.playerController.getPlayer().getPosition().equals(tileStart));
    }

    @Test
    public void testWalkAroundMonster() {
        Vector2f tileStart = new Vector2f(2, 1);
        Vector2f tileTo = new Vector2f(2, 3);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.movePlayerRight();
        mockWorld.playerController.movePlayerForward();
        mockWorld.playerController.movePlayerForward();
        mockWorld.playerController.movePlayerLeft();
        assert ( mockWorld.playerController.getPlayer().getPosition().equals(tileTo));
    }
}
