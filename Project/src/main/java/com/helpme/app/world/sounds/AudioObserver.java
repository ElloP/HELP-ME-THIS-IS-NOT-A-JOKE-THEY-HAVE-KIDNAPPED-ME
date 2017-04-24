package com.helpme.app.world.sounds;

import com.helpme.app.world.character.Event;
import com.helpme.app.world.character.IReadMonster;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-04-23.
 */
public class AudioObserver implements Observer {
    IReadMonster player;
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
                healthEvent((IReadMonster) o);
                break;
            case Position:
                posEvent((IReadMonster) o);
                break;
            case Direction:
                break;
        }
    }

    private boolean isPlayer(IReadMonster monster) {
        return player.equals(monster);
    }

    private AbstractMonsterSource getSource(IReadMonster monster) {
        for (AbstractMonsterSource monsterSource : monsterSources) {
            if (monsterSource.equals(monster)) {
                return monsterSource;
            }
        }
        return null;
    }

    private void posEvent(IReadMonster monster) {
        getSource(monster).playWalking();
    }

    private void healthEvent(IReadMonster monster) {
        getSource(monster).playHurting();
    }
}
