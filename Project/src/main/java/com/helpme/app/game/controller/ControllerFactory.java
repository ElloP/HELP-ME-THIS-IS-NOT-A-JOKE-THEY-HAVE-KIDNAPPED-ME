package com.helpme.app.game.controller;

import com.helpme.app.engine.audio.AudioHandler;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.consciousness.IConsciousness;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.view.BodyView;
import com.helpme.app.game.view.HealthView;
import com.helpme.app.game.view.camera.PlayerCameraView;
import com.helpme.app.game.view.resources.Resources;
import com.helpme.app.game.view.sources.AbstractBodySource;
import com.helpme.app.game.view.sources.BodySource;
import com.helpme.app.game.view.sources.PlayerBodySource;
import com.helpme.app.game.view.sources.Source;
import com.helpme.app.utils.mathl.Vector2f;
import com.helpme.app.utils.maybe.Maybe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

/**
 * Created by Jesper on 2017-05-24.
 */
final class ControllerFactory {
    private ControllerFactory(){

    }

    public static Observer createLevelAudioController(IConsciousness player, IConsciousness[] enemies) {
        AudioHandler.setListenerPos(player.readBody().readPosition().x, player.readBody().readPosition().y, 0);
        final Source[] walkSource = new Source[1];
        Resources.getSound("footstep").run(b -> walkSource[0] = new Source(b));
        final Source[] groanSource = new Source[1];
        Resources.getSound("groan").run(b -> groanSource[0] = new Source(b));
        final Source[] blockedSource = new Source[1];
        Resources.getSound("wallMove").run(b -> blockedSource[0] = new Source(b));
        final Source[] defaultSource = new Source[1];
        Resources.getSound("default").run(b -> defaultSource[0] = new Source(b));

        HashMap<String, Source> sourceMap = new HashMap<>();
        sourceMap.put(AbstractBodySource.WALKING, walkSource[0]);
        sourceMap.put(AbstractBodySource.BREATHING, groanSource[0]);
        sourceMap.put(AbstractBodySource.BLOCKED, blockedSource[0]);


        ArrayList<AbstractBodySource> bodySources = new ArrayList<>();
        for (IConsciousness enemy : enemies) {
            bodySources.add(new BodySource(enemy.readBody(), enemy.readBody().readPosition().x, enemy.readBody().readPosition().y, 0, sourceMap, defaultSource[0]));
        }
        bodySources.add(new PlayerBodySource(player.readBody(), player.readBody().readPosition().x, player.readBody().readPosition().y, 0, sourceMap, defaultSource[0]));
        LevelAudioController levelAudioController = new LevelAudioController(bodySources);
        return levelAudioController;
    }

    static Scene createLevelController(ILevel level, PlayerCameraView playerCameraView, PlayerController playerController){
        return new LevelController(level, playerCameraView, playerController);
    }

    static PlayerController createPlayerController(PlayerCameraView playerCameraView, IConsciousness player){
        HealthView healthView = new HealthView(new Vector2f(1200, 800));
        return new PlayerController(playerCameraView, player, healthView);
    }

    static Observer createEnemyController(BodyView bodyView) {
        return new EnemyController(bodyView);
    }

}
