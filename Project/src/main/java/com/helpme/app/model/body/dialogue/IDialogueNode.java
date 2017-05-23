package com.helpme.app.model.body.dialogue;

import com.helpme.app.utils.interfaces.ICopyable;

/**
 * Created by Klas on 2017-04-12.
 */
public interface IDialogueNode extends ICopyable<IDialogueNode> {
    IDialogueNode chooseDialogueOption(int nr);
    String[] getAlternatives();
    String getResponse();
    int getLength();
    String getInitialFrase();
}
