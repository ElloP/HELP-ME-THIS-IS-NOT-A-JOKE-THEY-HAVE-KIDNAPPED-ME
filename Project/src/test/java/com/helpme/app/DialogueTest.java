package com.helpme.app;

import com.helpme.app.Mock.MockWorld0;
import com.helpme.app.utils.Tuple.Tuple2;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.world.character.IReadMonster;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Jacob on 2017-04-11.
 */
public class DialogueTest {
    private MockWorld0 mockWorld;

    @Before
    public void setUp() {

        mockWorld = new MockWorld0();

    }

    @Test
    public void testTalkToMonster() {
        Vector2f tileStart = new Vector2f(8, 5);
        mockWorld.playerHandler.setPlayerPosition(tileStart);
        mockWorld.playerHandler.rotatePlayerLeft();
        Maybe<IReadMonster> maybeMonster = mockWorld.level.readMonster(new Vector2f(7,5));
        IReadMonster monster = maybeMonster.getValue();

        Tuple2<String,String[]> result = mockWorld.playerHandler.usePlayerTalk().getValue();
        Tuple2<String,String[]> monsterResponse = Maybe.wrap(monster.getDialogue()).getValue();

        assert (result.a.equals(monsterResponse.a));
        for(int i = 0; i < result.b.length; i++){
            //System.out.println(result.b[i]);
            assert (result.b[i].equals(monsterResponse.b[i]));
        }

        //Go through all responses
        for (int i = result.b.length-1; i >= -1; i--){
            if(i == -1) break;
            System.out.println(result.b[i]);
            Maybe<Tuple2<String,String[]>> maybeResult = mockWorld.playerHandler.usePlayerTalk(i);
            if(maybeResult.isNothing()) break;
            result = maybeResult.getValue();
            System.out.println(result.a);
            for (String str : result.b) {
                System.out.println("- " + str);
            }

        }



    }

    @Test
    public void badArgumentInTalk(){
        Vector2f tileStart = new Vector2f(8, 5);
        mockWorld.playerHandler.setPlayerPosition(tileStart);
        mockWorld.playerHandler.rotatePlayerLeft();
        try {
            Tuple2<String,String[]> result = mockWorld.playerHandler.usePlayerTalk(5).getValue();
        } catch (IllegalArgumentException e){
            assert(e.toString().equals("java.lang.IllegalArgumentException: Index larger than length"));
        }
    }
    @Test
    public void badArgumentInTalk2(){
        Vector2f tileStart = new Vector2f(8, 5);
        mockWorld.playerHandler.setPlayerPosition(tileStart);
        mockWorld.playerHandler.rotatePlayerLeft();
        try {
            Tuple2<String,String[]> result = mockWorld.playerHandler.usePlayerTalk(-2).getValue();
        } catch (IllegalArgumentException e){
            assert(e.toString().equals("java.lang.IllegalArgumentException: Index smaller than -1"));
        }
    }

    @Test
    public void testTalkToNothing() {
        Vector2f tileStart = new Vector2f(0, 0);
        mockWorld.playerHandler.setPlayerPosition(tileStart);
        Maybe<Tuple2<String,String[]>> result = mockWorld.playerHandler.usePlayerTalk();
        assert (result.isNothing());
    }
}
