package com.helpme.app;

import com.helpme.app.Mock.MockWorld;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Vector2f;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jacob on 2017-04-11.
 */
public class DialogueTest {
    private MockWorld mockWorld;

    @Before
    public void setUp() {
        mockWorld = new MockWorld();
    }

    @Test
    public void testTalkToMonster() {
        Vector2f tileStart = new Vector2f(2, 1);
        mockWorld.playerController.setPlayerPosition(tileStart);
        Tuple2<String,String[]> result =  mockWorld.playerController.usePlayerTalk();
        assert (result.equals(mockWorld.level.getMonster(new Vector2f(2, 2)).initiateDialogue()));
    }

    @Test
    public void testTalkToNothing() {
        Vector2f tileStart = new Vector2f(0, 0);
        mockWorld.playerController.setPlayerPosition(tileStart);
        Tuple2<String,String[]> result =  mockWorld.playerController.usePlayerTalk();
        assert (result == null);
    }
}
