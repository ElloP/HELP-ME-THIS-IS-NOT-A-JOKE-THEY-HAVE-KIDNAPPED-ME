package com.helpme.app.world.character.dialogue;

/**
 * Created by Klas on 2017-04-12.
 */
public interface IDialogueNode {
    IDialogueNode chooseDialogueOption(int nr);
    String[] getAlternatives();
    String getResponse();
    int getLength();
    String getInitialFrase();
}
