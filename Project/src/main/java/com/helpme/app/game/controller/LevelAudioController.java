package com.helpme.app.game.controller;

import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.body.concrete.BodyEvent;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.view.sources.AbstractBodySource;
import com.helpme.app.utils.maybe.Just;
import com.helpme.app.utils.maybe.Maybe;
import com.helpme.app.utils.maybe.Nothing;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-04-23.
 */
public class LevelAudioController implements Observer {
    private ArrayList<AbstractBodySource> bodySources;

    LevelAudioController(ArrayList<AbstractBodySource> bodySources) {
        this.bodySources = bodySources;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (!(arg instanceof BodyEvent) || !(o instanceof IConsciousness)) {
            return;
        }
        BodyEvent event = (BodyEvent) arg;
        IConsciousness consciousness = (IConsciousness) o;
        switch (event) {
            case DEAD:
                break;
            case HEALTH:
                healthEvent(consciousness.readBody());
                break;
            case POSITION:
                posEvent(consciousness.readBody());
                break;
            case DIRECTION:
                break;
            case BLOCKED:
                System.out.println("Being blocked");
                getSource(consciousness.readBody()).play(AbstractBodySource.BLOCKED);
                break;
            default:
                break;
        }
    }


    private AbstractBodySource getSource(IReadBody body) {
        for (AbstractBodySource bodySource : bodySources) {
            if (bodySource.equals(body)) {
                return bodySource;
            }
        }
        return null;
    }

    private void posEvent(IReadBody body) {
        getSource(body).play(AbstractBodySource.WALKING);
    }

    private void healthEvent(IReadBody body) {
        getSource(body).play(AbstractBodySource.HURTING);
    }
}
