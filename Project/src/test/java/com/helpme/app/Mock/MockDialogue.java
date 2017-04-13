package com.helpme.app.Mock;

import com.helpme.app.character.Dialogue;
import com.helpme.app.character.IDialogue;
import com.helpme.app.utils.Tuple.Tuple2;

/**
 * Created by Klas on 2017-04-13.
 */
public class MockDialogue {

    private Tuple2<String,String>[] mockDialogue;
    public IDialogue dialogue;

    public MockDialogue(){
        mockDialogue = new Tuple2[5];
        mockDialogue[0] = new Tuple2<>("What's up?", "Nothing much");
        mockDialogue[1] = new Tuple2<>("Who are you?", "I am death, the destroyer of worlds");
        mockDialogue[2] = new Tuple2<>("What can you tell me about this place?", "Nothing");
        mockDialogue[3] = new Tuple2<>("What is 5 times 5", "Smoke");
        mockDialogue[4] = new Tuple2<>("Who is Olle?", "A jolly good fellow");
        dialogue = new Dialogue("Hello!",mockDialogue);
    }
}
