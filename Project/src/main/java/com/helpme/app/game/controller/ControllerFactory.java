package com.helpme.app.game.controller;

import com.helpme.app.engine.audio.AudioHandler;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.view.BodyView;
import com.helpme.app.game.view.HealthView;
import com.helpme.app.game.view.UIObjectView;
import com.helpme.app.game.view.camera.PlayerCameraView;
import com.helpme.app.game.view.resources.Resources;
import com.helpme.app.game.view.sources.AbstractBodySource;
import com.helpme.app.game.view.sources.BodySource;
import com.helpme.app.game.view.sources.PlayerSource;
import com.helpme.app.game.view.sources.Source;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-24.
 */
final class ControllerFactory {
    private ControllerFactory(){

    }

    public static Observer createLevelAudioController(IReadBody playerBody, List<IReadBody> enemies) {
        AudioHandler.setListenerPos(playerBody.readPosition().x, playerBody.readPosition().y, 0);
        Maybe<Integer> walkBuffer = Resources.getSound("footstep");
        Maybe<Integer> groanBuffer = Resources.getSound("groan");

        ArrayList<AbstractBodySource> bodySources = new ArrayList<>();
        for (IReadBody body : enemies) {
            bodySources.add(new BodySource(body, new Source(), walkBuffer.isJust() ? walkBuffer.getValue() : 0, -1, groanBuffer.isJust() ? walkBuffer.getValue() : 0, body.readPosition().x, body.readPosition().y, 0));
        }
        bodySources.add(new PlayerSource(playerBody, new Source(), walkBuffer.isJust() ? walkBuffer.getValue() : 0, -1, groanBuffer.isJust() ? groanBuffer.getValue() : 0, playerBody.readPosition()));
        LevelAudioController levelAudioController = new LevelAudioController(bodySources);
        return levelAudioController;
    }

    static Scene createLevelController(ILevel level, PlayerCameraView playerCameraView){
        HealthView healthView1 = new HealthView(new Vector2f(1200, 800));
        UIObjectView healthView = new UIObjectView("health", new Vector2f(1300, 800), 200, 200);
        return new LevelController(level, healthView1, playerCameraView);
    }

    static Observer createPlayerController(PlayerCameraView playerCameraView, IConsciousness player){
        return new PlayerController(playerCameraView, player);
    }

    static Observer createEnemyController(BodyView bodyView) {
        return new EnemyController(bodyView);
    }

}
