package com.helpme.app.character.dialogue;

import com.helpme.app.utils.Tuple.Tuple2;

/**
 * Created by Klas on 2017-04-12.
 */
public interface IDialogue {
    Tuple2<String,String[]> initiateDialogue();
    Tuple2<String,String[]> chooseDialogue(int i);
    void resetDialogue();
}
