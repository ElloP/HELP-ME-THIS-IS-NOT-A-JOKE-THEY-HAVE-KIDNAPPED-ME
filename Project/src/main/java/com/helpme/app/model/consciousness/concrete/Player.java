package com.helpme.app.model.consciousness.concrete;

import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.model.body.IBody;
import com.helpme.app.model.body.IReadBody;
import com.helpme.app.utils.tuple.Tuple2;
import com.helpme.app.model.consciousness.ISurroundings;

/**
 * Created by Jacob on 2017-04-08.
 */
public class Player extends Consciousness {

    public Player(IBody body, ISurroundings level) {
        super(body, level);
    }

    public void update() {

    }

    @Override
    public Maybe<Tuple2<String, String[]>> useTalk() {

        Maybe<IReadBody> maybeBody = surroundings.readFacing(body);

        return maybeBody.chain(m -> m.getDialogue());
    }

    @Override
    public Maybe<Tuple2<String, String[]>> useTalk(int dialogueSelect) throws IllegalArgumentException {
        Maybe<IReadBody> maybeBody = surroundings.readFacing(body);

        return maybeBody.chain(m -> m.getResponse(dialogueSelect));

    }

}
