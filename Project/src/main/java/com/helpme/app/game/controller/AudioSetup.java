package com.helpme.app.game.controller;

import com.helpme.app.engine.sounds.audio.AudioHandler;
import com.helpme.app.engine.sounds.sources.AbstractBodySource;
import com.helpme.app.engine.sounds.sources.BodySource;
import com.helpme.app.engine.sounds.sources.PlayerSource;
import com.helpme.app.engine.sounds.sources.Source;
import com.helpme.app.game.model.body.IReadBody;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-24.
 */
public class AudioSetup {
    public static Observer setupAudioController(IReadBody playerBody, List<IReadBody> enemies) {


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
}
