package com.helpme.app.character;

import com.helpme.app.utils.Tuple.Tuple2;

/**
 * Created by Klas on 2017-04-12.
 */
public class DialogueNode implements IDialogueNode {
    private String response;
    private Tuple2<String,IDialogueNode>[] dialogueAlternatives;

    public DialogueNode(String response, Tuple2<String,String>[] nodes){
        this.response = response;
        dialogueAlternatives = new Tuple2[nodes.length];
        for(int i = 0; i < dialogueAlternatives.length; i++){
            dialogueAlternatives[i] = new Tuple2<>(nodes[i].a,new DialogueNode(nodes[i].b,null));
        }

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
        return dialogueAlternatives[nr].b;
    }

    public String[] getAlternatives(){
        String[] result = new String[dialogueAlternatives.length];
        for(int i = 0; i < dialogueAlternatives.length; i++){
            result[i] = i + ". " + dialogueAlternatives[i].a;
        }
        return result;
    }

    @Override
    public String getResponse() {
        return response;
    }

}
