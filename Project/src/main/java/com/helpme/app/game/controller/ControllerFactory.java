package com.helpme.app.game.controller;

import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.sounds.audio.AudioHandler;
import com.helpme.app.engine.sounds.sources.AbstractBodySource;
import com.helpme.app.engine.sounds.sources.BodySource;
import com.helpme.app.engine.sounds.sources.PlayerSource;
import com.helpme.app.engine.sounds.sources.Source;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.view.BodyView;
import com.helpme.app.game.view.HealthView;
import com.helpme.app.game.view.UIObjectView;
import com.helpme.app.game.view.camera.PlayerCameraView;
import com.helpme.app.utils.mathl.Vector2f;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-24.
 */
final class ControllerFactory {
    private ControllerFactory(){

    }

    static Observer createLevelAudioController(IReadBody playerBody, List<IReadBody> enemies) {
        try {
            AudioHandler.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AudioHandler.setListenerPos(playerBody.readPosition().x, playerBody.readPosition().y, 0);
        int walkBuffer = 0;
        int groanBuffer = 0;
        try {
            walkBuffer = AudioHandler.loadSound("src\\main\\java\\com\\helpme\\app\\engine\\sounds\\files\\Cowboy.wav");
            groanBuffer = AudioHandler.loadSound("src\\main\\java\\com\\helpme\\app\\engine\\sounds\\files\\Groan.wav");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<AbstractBodySource> bodySources = new ArrayList<>();
        for (IReadBody body : enemies) {
            bodySources.add(new BodySource(body, new Source(), walkBuffer, -1, groanBuffer, body.readPosition().x, body.readPosition().y, 0));
        }
        bodySources.add(new PlayerSource(playerBody, new Source(), walkBuffer, -1, groanBuffer, playerBody.readPosition()));
        LevelAudioController levelAudioController = new LevelAudioController(bodySources);
        return levelAudioController;
    }

    static Scene createLevelController(ILevel level, PlayerCameraView playerCameraView){
        HealthView healthView = new HealthView(new Vector2f(1200, 800));
        return new LevelController(level, healthView, playerCameraView);
    }

    static Observer createPlayerController(PlayerCameraView playerCameraView, IConsciousness player){
        return new PlayerController(playerCameraView, player);
    }

    static Observer createEnemyController(BodyView bodyView) {
        return new EnemyController(bodyView);
    }

}
