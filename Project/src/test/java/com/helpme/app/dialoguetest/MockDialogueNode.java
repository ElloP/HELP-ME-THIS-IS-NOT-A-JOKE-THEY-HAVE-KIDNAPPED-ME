package com.helpme.app.dialoguetest;

import com.helpme.app.world.character.dialogue.IDialogueNode;

/**
 * Created by kopa on 2017-05-15.
 */
public class MockDialogueNode implements IDialogueNode {
    @Override
    public IDialogueNode chooseDialogueOption(int nr) {
        return null;
    }

    @Override
    public String[] getAlternatives() {
        return new String[0];
    }

    @Override
    public String getResponse() {
        return null;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public String getInitialFrase() {
        return null;
    }
}
