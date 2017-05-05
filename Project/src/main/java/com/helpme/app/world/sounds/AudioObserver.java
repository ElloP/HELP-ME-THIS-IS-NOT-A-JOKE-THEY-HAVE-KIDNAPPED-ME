package com.helpme.app.world.sounds;

import com.helpme.app.world.character.Event;
import com.helpme.app.world.character.IReadBody;
import com.helpme.app.world.sounds.Source.AbstractMonsterSource;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-04-23.
 */
public class AudioObserver implements Observer {
    IReadBody player;
    ArrayList<AbstractMonsterSource> monsterSources;

    public AudioObserver(ArrayList<AbstractMonsterSource> monsterSources) {
        this.monsterSources = monsterSources;
    }

    @Override
    public void update(Observable o, Object arg) {
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

    private boolean isPlayer(IReadBody monster) {
        return player.equals(monster);
    }

    private AbstractMonsterSource getSource(IReadBody monster) {
        for (AbstractMonsterSource monsterSource : monsterSources) {
            if (monsterSource.equals(monster)) {
                return monsterSource;
            }
        }
        return null;
    }

    private void posEvent(IReadBody monster) {
        getSource(monster).playWalking();
    }

    private void healthEvent(IReadBody monster) {
        getSource(monster).playHurting();
    }
}
