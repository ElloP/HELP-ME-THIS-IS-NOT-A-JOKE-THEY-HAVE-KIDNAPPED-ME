package com.helpme.app.game.model.body.dialogue;

import com.helpme.app.utils.tuple.Tuple2;

/**
 * Created by Klas on 2017-04-12.
 */
public class DialogueNode implements IDialogueNode {
    private Tuple2<String,String> dialogue;
    private IDialogueNode[] dialogueAlternatives;

    public DialogueNode(String initialFrase, String response, IDialogueNode[] nodes){
        this.dialogue = new Tuple2<>(initialFrase,response);
        this.dialogueAlternatives = nodes == null ? new IDialogueNode[0] : nodes;
    }

    public DialogueNode(Tuple2<String,String> dialogue, IDialogueNode[] dialogueAlternatives){
        this.dialogue = dialogue;
        this.dialogueAlternatives = dialogueAlternatives;
    }

    @Override
    public int getLength(){
        return dialogueAlternatives.length;
    }

    @Override
    public String getInitialFrase() {
        if(dialogue == null) return "";
        return dialogue.a;
    }

    @Override
    public String getResponse() {
        if(dialogue == null) return null;
        return dialogue.b;
    }

    @Override
    public IDialogueNode chooseDialogueOption(int nr) {
        if(nr >= dialogueAlternatives.length){
            System.out.println("Integer exceeds length of dialogueAlternatives in DialogueNode.chooseDialogOption()");
            return null;
        }
        else if(nr < 0){
            System.out.println("Negative integer in DialogueNode.chooseDialogOption()");
            return null;
        }
        return dialogueAlternatives[nr];
    }

    public String[] getAlternatives(){
        if(dialogueAlternatives == null) {
            return new String[0];
        }

        String[] result = new String[dialogueAlternatives.length];
        for(int i = 0; i < dialogueAlternatives.length; i++){
            result[i] = i + ". " + dialogueAlternatives[i].getInitialFrase();
        }
        return result;
    }


    @Override
    public IDialogueNode copy() {
        return new DialogueNode(dialogue.copy(), dialogueAlternatives.clone());
    }
}
