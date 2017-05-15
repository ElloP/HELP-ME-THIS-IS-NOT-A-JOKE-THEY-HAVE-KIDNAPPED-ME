package com.helpme.app.dialoguetest;

import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.character.dialogue.Dialogue;
import com.helpme.app.world.character.dialogue.DialogueNode;
import com.helpme.app.world.character.dialogue.IDialogue;
import com.helpme.app.world.character.dialogue.IDialogueNode;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kopa on 2017-05-14.
 */
public class DialogueTest {
    IDialogue dialogue;

    @Before
    public void setup(){
        dialogue = new Dialogue("Hello World", new IDialogueNode[]{new MockDialogueNode(), new MockDialogueNode()});
    }


    /*
    @Test
    public void testTalkTo() {
        Vector2f tileStart = new Vector2f(8, 5);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.rotatePlayerLeft();
        Maybe<IReadBody> maybeBody = mockWorld.level.readBody(new Vector2f(7,5));
        IReadBody body = maybeBody.getValue();

        Tuple2<String,String[]> result = mockWorld.player.usePlayerTalk().getValue();
        Tuple2<String,String[]> response = Maybe.wrap(body.getDialogue()).getValue();

        assert (result.a.equals(response.a));
        for(int i = 0; i < result.b.length; i++){
            //System.out.println(result.b[i]);
            assert (result.b[i].equals(response.b[i]));
        }

        //Go through all responses
        for (int i = result.b.length-1; i >= -1; i--){
            if(i == -1) break;
            System.out.println(result.b[i]);
            Maybe<Tuple2<String,String[]>> maybeResult = mockWorld.player.usePlayerTalk(i);
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
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.rotatePlayerLeft();
        try {
            Tuple2<String,String[]> result = mockWorld.player.usePlayerTalk(5).getValue();
        } catch (IllegalArgumentException e){
            assert(e.toString().equals("java.lang.IllegalArgumentException: Index larger than length"));
        }
    }
    @Test
    public void badArgumentInTalk2(){
        Vector2f tileStart = new Vector2f(8, 5);
        mockWorld.player.setPlayerPosition(tileStart);
        mockWorld.player.rotatePlayerLeft();
        try {
            Tuple2<String,String[]> result = mockWorld.player.usePlayerTalk(-2).getValue();
        } catch (IllegalArgumentException e){
            assert(e.toString().equals("java.lang.IllegalArgumentException: Index smaller than -1"));
        }
    }

    @Test
    public void testTalkToNothing() {
        Vector2f tileStart = new Vector2f(0, 0);
        mockWorld.player.setPlayerPosition(tileStart);
        Maybe<Tuple2<String,String[]>> result = mockWorld.player.usePlayerTalk();
        assert (result.isNothing());
    }*/
}
