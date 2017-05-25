package com.helpme.app.game.controller;

import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.body.concrete.BodyEvent;
import com.helpme.app.game.view.sources.AbstractBodySource;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-04-23.
 */
public class LevelAudioController implements Observer {
    IReadBody player;
    ArrayList<AbstractBodySource> bodySources;
    public LevelAudioController(ArrayList<AbstractBodySource> bodySources) {
        this.bodySources = bodySources;
    }

    @Override
    public void update(Observable o, Object arg) {

        if (!(arg instanceof BodyEvent) || !(o instanceof IReadBody)) {
            return;
        }
        BodyEvent event = (BodyEvent) arg;
        switch (event) {
            case DEAD:
                break;
            case HEALTH:
                healthEvent((IReadBody) o);
                break;
            case POSITION:
                posEvent((IReadBody) o);
                break;
            case DIRECTION:
                break;
        }
    }

    private boolean isPlayer(IReadBody body) {
        return player.equals(body);
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
        getSource(body).playWalking();
    }

    private void healthEvent(IReadBody body) {
        getSource(body).playHurting();
    }
}
