package com.helpme.app.engine.sounds;

import com.helpme.app.model.body.concrete.visitor.Event;
import com.helpme.app.model.body.IReadBody;
import com.helpme.app.engine.sounds.sources.AbstractBodySource;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-04-23.
 */
public class AudioObserver implements Observer {
    IReadBody player;
    ArrayList<AbstractBodySource> bodySources;

    public AudioObserver(ArrayList<AbstractBodySource> bodySources) {
        this.bodySources = bodySources;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof Event) || !(o instanceof IReadBody)) {
            return;
        }
        Event event = (Event) arg;
        switch (event) {
            case Dead:
                break;
            case Health:
                healthEvent((IReadBody) o);
                break;
            case Position:
                posEvent((IReadBody) o);
                break;
            case Direction:
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
