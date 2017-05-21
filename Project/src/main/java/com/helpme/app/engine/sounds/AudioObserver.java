package com.helpme.app.engine.sounds;

import com.helpme.app.world.body.concrete.visitor.WorldEvent;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.engine.sounds.sources.AbstractMonsterSource;

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
        System.out.println(monsterSources);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Inside audioObserver");
        System.out.println(arg);
        WorldEvent worldEvent = (WorldEvent) arg;
        switch (worldEvent) {
            case DEAD:
                break;
            case Health:
                healthEvent((IReadBody) o);
                break;
            case Position:
                posEvent((IReadBody) o);
                break;
            case MOV_FORWARD:
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
