package com.helpme.app.character;

import com.helpme.app.utils.Tuple.Tuple2;

/**
 * Created by Klas on 2017-04-12.
 */
public interface IDialogueNode {
    IDialogueNode chooseDialogueOption(int nr);
    String[] getAlternatives();
    String getResponse();
    int getLength();
}
