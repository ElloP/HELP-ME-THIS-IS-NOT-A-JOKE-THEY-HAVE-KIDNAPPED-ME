package com.helpme.app.character;

import com.helpme.app.utils.Tuple.Tuple2;

import java.util.Collection;
import java.util.List;

/**
 * Created by Klas on 2017-04-12.
 */
public class Dialogue implements IDialogue{
    private final IDialogueNode greeting;
    private final String continueFrase;
    private IDialogueNode current;

    public Dialogue(String greeting, IDialogueNode[] nodes) {
        this.continueFrase = "Anything else?";
        this.greeting = new DialogueNode(continueFrase,greeting,nodes);
        this.current = this.greeting;
    }

    @Override
    public Tuple2<String, String[]> initiateDialogue() {
        resetDialogue();
        return new Tuple2<>(current.getResponse(),current.getAlternatives());
    }

    @Override
    public Tuple2<String, String[]> chooseDialogue(int i) throws IllegalArgumentException {
        if(i == -1) return null;
        if(i < -1) throw new IllegalArgumentException("Index smaller than -1");
        if(i >= current.getLength()) throw new IllegalArgumentException("Index larger than length");
        current = current.chooseDialogueOption(i);

        if(current.getResponse() == null) {
            resetDialogue();
            return new Tuple2<>(continueFrase,current.getAlternatives());
        }
        String[] alternatives = current.getAlternatives();
        String response = current.getResponse();
        if(alternatives == null){
            resetDialogue();
            response += "\n" + continueFrase;
            alternatives = current.getAlternatives();

        }
        return new Tuple2<>(response,alternatives);
    }

    @Override
    public void resetDialogue() {
        current = greeting;
    }
}
