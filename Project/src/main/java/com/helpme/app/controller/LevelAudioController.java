package com.helpme.app.controller;
import com.helpme.app.model.body.IReadBody;
import com.helpme.app.engine.sounds.sources.AbstractBodySource;
import com.helpme.app.model.body.concrete.visitor.WorldEvent;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Jesper on 2017-04-23.
 */
public class LevelAudioController implements IController {
    IReadBody player;
    ArrayList<AbstractBodySource> bodySources;
    public LevelAudioController(ArrayList<AbstractBodySource> bodySources) {
        this.bodySources = bodySources;
    }

    @Override
    public void update(Observable o, Object arg) {
        WorldEvent worldEvent = (WorldEvent) arg;
        switch (worldEvent) {
            case DEAD:
                if (!(arg instanceof WorldEvent) || !(o instanceof IReadBody)) {
                    return;
                }
                WorldEvent event = (WorldEvent) arg;
                switch (event) {
                    case DEAD:
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
