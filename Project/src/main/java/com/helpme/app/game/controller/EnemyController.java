package com.helpme.app.game.controller;

import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.body.concrete.BodyEvent;
import com.helpme.app.game.view.BodyView;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-25.
 * An observer class that observes an enemy and updates the a BodyView.
 */
public class EnemyController implements Observer{
    private BodyView enemyBody;

    public EnemyController(BodyView enemyBody) {
        this.enemyBody = enemyBody;
    }
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof BodyEvent) {
            BodyEvent event = (BodyEvent) arg;
            switch (event) {
                case POSITION:
                    if(o instanceof IReadBody){
                        enemyBody.setPosition(((IReadBody) o).readPosition());
                    }
                    break;
                case DEAD:
                    enemyBody.kill();
                    break;
                case HEALTH:
                    break;
                default:
                    break;
            }
        }
    }
}
