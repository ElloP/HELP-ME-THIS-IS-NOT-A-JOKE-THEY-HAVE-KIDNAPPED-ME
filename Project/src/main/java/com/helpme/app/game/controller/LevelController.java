package com.helpme.app.game.controller;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.base.Time;
import com.helpme.app.engine.renderer.base.Texture;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.view.BodyView;
import com.helpme.app.game.view.HealthView;
import com.helpme.app.game.view.LevelView;
import com.helpme.app.game.view.camera.PlayerCameraView;
import com.helpme.app.game.view.resources.Resources;
import com.helpme.app.utils.maybe.Maybe;

import java.util.Observer;

/**
 * Created by Jesper on 2017-05-22.
 * The main scene for when in-game. Adds a LevelView, BodyViews and PlayerController as children that it then draws.
 */
public class LevelController extends Scene {
    private PlayerCameraView playerCameraView;

    public LevelController(ILevel level, PlayerCameraView playerCameraView, PlayerController playerController) {
        this.playerCameraView = playerCameraView;

        addChild(new LevelView(level));
        for (IReadBody body : level.readBodies()) {

            Maybe<Texture> maybeTexture = Resources.getTexture("klas");
            BodyView bodyView = new BodyView(body.readPosition().x, body.readPosition().y, maybeTexture.isJust() ? maybeTexture.getValue() : null);
            addChild(bodyView);
            Observer enemyController = ControllerFactory.createEnemyController(bodyView);
            body.addObserver(enemyController);

        }
        addChild(playerController);

    }

    @Override
    public void draw(ICamera camera) {
        for (GameObject child : children) {
            if (child.isActive()) {
                child.drawAll(playerCameraView.getCamera());
            }
        }
    }

    @Override
    public void input(Time time) {
        playerCameraView.update();
    }

    public PlayerCameraView getPlayerCameraView() {
        return playerCameraView;
    }
}
