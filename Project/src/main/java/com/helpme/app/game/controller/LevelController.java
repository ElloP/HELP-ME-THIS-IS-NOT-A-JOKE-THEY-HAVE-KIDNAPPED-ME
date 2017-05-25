package com.helpme.app.game.controller;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.base.Time;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.view.BodyView;
import com.helpme.app.game.view.LevelView;
import com.helpme.app.game.view.UIObjectView;
import com.helpme.app.game.view.camera.PlayerCameraView;

/**
 * Created by Jesper on 2017-05-22.
 */
public class LevelController extends Scene {
    private UIObjectView healthView;
    private PlayerCameraView playerCameraView;

    public LevelController(ILevel level, UIObjectView healthView, PlayerCameraView playerCameraView) {
        this.healthView = healthView;
        this.playerCameraView = playerCameraView;

        addChild(new LevelView(level));
        for (IReadBody body : level.readBodies()) {
            addChild(new BodyView(body.readPosition().x, body.readPosition().y));
        }

    }
    public void updateHealth(int newHealth) {
        if (newHealth > 80) {
            healthView.setTexture("healthView");
        } else if (newHealth <= 80 && newHealth > 60) {
            healthView.setTexture("health80");
        }
    }

    @Override
    public void draw(ICamera camera) {
        for (GameObject child : children) {
            child.drawAll(playerCameraView.getCamera());
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
