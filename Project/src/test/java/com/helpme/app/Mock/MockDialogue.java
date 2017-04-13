package com.helpme.app.Mock;

import com.helpme.app.character.Dialogue;
import com.helpme.app.character.DialogueNode;
import com.helpme.app.character.IDialogue;
import com.helpme.app.character.IDialogueNode;
import com.helpme.app.utils.Tuple.Tuple2;

/**
 * Created by Klas on 2017-04-13.
 */
public class MockDialogue {

    private IDialogueNode[] mockDialogue;
    public IDialogue dialogue;

    public MockDialogue(){
        mockDialogue = new IDialogueNode[5];
        mockDialogue[0] = new DialogueNode("What's up?", "Nothing much", null);
        mockDialogue[1] = new DialogueNode("Who are you?", "I am death, the destroyer of worlds", null);
        mockDialogue[2] = new DialogueNode("What can you tell me about this place?", "Nothing", null);
        mockDialogue[3] = new DialogueNode("What is 5 times 5", "Smoke", null);
        mockDialogue[4] = new DialogueNode("Who is Olle?", "A jolly good fellow", null);
        dialogue = new Dialogue("Hello!",mockDialogue);
    }
}
