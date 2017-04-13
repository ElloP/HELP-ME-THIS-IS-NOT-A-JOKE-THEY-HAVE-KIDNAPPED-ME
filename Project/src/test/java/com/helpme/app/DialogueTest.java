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
        Vector2f tileStart = new Vector2f(8, 5);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.rotatePlayerLeft();
        Tuple2<String,String[]> result =  mockWorld.playerController.usePlayerTalk();
        Tuple2<String,String[]> monsterRespons = mockWorld.level.getMonster(new Vector2f(7, 5)).initiateDialogue();
        assert (result.a.equals(monsterRespons.a));
        for(int i = 0; i < result.b.length; i++){
            //System.out.println(result.b[i]);
            assert (result.b[i].equals(monsterRespons.b[i]));
        }

        //Go through all responses
        for (int i = result.b.length-1; i >= -1; i--){
            if(i == -1) break;
            System.out.println(result.b[i]);
            result = mockWorld.playerController.usePlayerTalk(i);
            if(result == null) break;
            System.out.println(result.a);
            for (String str : result.b) {
                System.out.println("- " + str);
            }
        }
    }

    @Test
    public void badArgumentInTalk(){
        Vector2f tileStart = new Vector2f(8, 5);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.rotatePlayerLeft();
        try {
            Tuple2<String,String[]> result =  mockWorld.playerController.usePlayerTalk(5);
        } catch (IllegalArgumentException e){
            assert(e.toString().equals("java.lang.IllegalArgumentException: Index larger than length"));
        }
    }
    @Test
    public void badArgumentInTalk2(){
        Vector2f tileStart = new Vector2f(8, 5);
        mockWorld.playerController.setPlayerPosition(tileStart);
        mockWorld.playerController.rotatePlayerLeft();
        try {
            Tuple2<String,String[]> result =  mockWorld.playerController.usePlayerTalk(-2);
        } catch (IllegalArgumentException e){
            assert(e.toString().equals("java.lang.IllegalArgumentException: Index smaller than -1"));
        }
    }

    @Test
    public void testTalkToNothing() {
        Vector2f tileStart = new Vector2f(0, 0);
        mockWorld.playerController.setPlayerPosition(tileStart);
        Tuple2<String,String[]> result =  mockWorld.playerController.usePlayerTalk();
        assert (result == null);
    }
}
