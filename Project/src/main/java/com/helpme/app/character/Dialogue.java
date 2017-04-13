package com.helpme.app.character;

import com.helpme.app.utils.Tuple.Tuple2;

/**
 * Created by Klas on 2017-04-12.
 */
public class Dialogue implements IDialogue{
    private final IDialogueNode greeting;
    private final String continueFrase;
    private IDialogueNode current;

    public Dialogue(String greeting, Tuple2<String,String>[] nodes) {
        this.greeting = new DialogueNode(greeting,nodes);
        this.current = this.greeting;
        this.continueFrase = "Anything else?";
    }

    @Override
    public Tuple2<String, String[]> initiateDialogue() {
        resetDialogue();
        return new Tuple2<>(current.getResponse(),current.getAlternatives());
    }

    @Override
    public Tuple2<String, String[]> chooseDialogue(int i) {
        if(i == -1) return null;
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
