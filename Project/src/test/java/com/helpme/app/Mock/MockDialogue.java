package com.helpme.app.Mock;

import com.helpme.app.world.body.dialogue.Dialogue;
import com.helpme.app.world.body.dialogue.DialogueNode;
import com.helpme.app.world.body.dialogue.IDialogue;
import com.helpme.app.world.body.dialogue.IDialogueNode;

/**
 * Created by Klas on 2017-04-13.
 */
public class MockDialogue {

    private IDialogueNode[] mockDialogue;
    public IDialogue dialogue0;
    public IDialogue dialogue1;

    public MockDialogue(){
        mockDialogue = new IDialogueNode[5];
        mockDialogue[0] = new DialogueNode("What's up?", "Nothing much", null);
        mockDialogue[1] = new DialogueNode("Who are you?", "I am death, the destroyer of worlds", null);
        mockDialogue[2] = new DialogueNode("What can you tell me about this place?", "Nothing", null);
        mockDialogue[3] = new DialogueNode("What is 5 times 5", "at least 10", null);
        mockDialogue[4] = new DialogueNode("Who is Olle?", "A jolly good fellow", null);
        dialogue0 = new Dialogue("Hello!",mockDialogue.clone());

    }
}
