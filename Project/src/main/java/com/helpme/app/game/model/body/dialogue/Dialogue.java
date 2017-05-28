package com.helpme.app.game.model.body.dialogue;

import com.helpme.app.utils.tuple.Tuple2;

/**
 * Created by Klas on 2017-04-12.
 *
 * Dialogue with tree-structure
 * Have an initial node that holds a list of alternatives (nodes).
 * If you choose an alternative you will continue with that nodes list.
 * You will loop through a dialogue tree until you reach a leaf, then you will return to the root
 *
 */
public class Dialogue implements IDialogue{
    private final IDialogueNode greeting;   // The initial dialogue
    private final String continuePhrase;    // After reaching a leaf, this will be said before going back to initial node
    private IDialogueNode current;          // Current node

    public Dialogue(String greeting, IDialogueNode[] nodes) {
        this.continuePhrase = "Anything else?";
        this.greeting = new DialogueNode(continuePhrase,greeting,nodes);
        this.current = this.greeting;
    }

    public Dialogue(String continuePhrase, IDialogueNode node) {
        this.continuePhrase = continuePhrase;
        this.greeting = node;
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
            return new Tuple2<>(continuePhrase,current.getAlternatives());
        }
        String[] alternatives = current.getAlternatives();
        String response = current.getResponse();
        if(alternatives == null){
            resetDialogue();
            response += "\n" + continuePhrase;
            alternatives = current.getAlternatives();

        }
        return new Tuple2<>(response,alternatives);
    }

    @Override
    public void resetDialogue() {
        current = greeting;
    }

    @Override
    public IDialogue copy(){
        return new Dialogue(continuePhrase, greeting.copy());
    }
}
