package com.helpme.app.game.controller;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.base.Time;
import com.helpme.app.game.model.body.IReadBody;
import com.helpme.app.game.model.level.ILevel;
import com.helpme.app.game.view.BodyView;
import com.helpme.app.game.view.LevelView;
import com.helpme.app.game.view.UIObjectView;
import com.helpme.app.game.view.camera.PlayerCamera;
import com.helpme.app.utils.Vector2f;
/**
 * Created by Jesper on 2017-05-22.
 */
public class LevelController extends Scene {
    private UIObjectView health;
    private ICamera playerCamera;

    private PlayerCamera cameraController;

    //TODO (Jesper): Should accept a player to read hp?
    public LevelController(ILevel level, IReadBody player, Time time) {
        addChild(new LevelView(level));
        for (IReadBody body : level.readBodies()) {
            addChild(new BodyView(body.readPosition().x, body.readPosition().y));
        }
        System.out.println(player.readDirection());
        this.playerCamera = new Camera(player.readPosition(), player.readDirection());
        health = new UIObjectView("health", new Vector2f(1300, 800), 200, 200);

        this.cameraController = new PlayerCamera(playerCamera, time);

    }
    public void updateHealth(int newHealth) {
        if (newHealth > 80) {
            health.setTexture("health");
        } else if (newHealth <= 80 && newHealth > 60) {
            health.setTexture("health80");
        }
    }

    @Override
    public void draw(ICamera camera) {
        for (GameObject child : children) {
            child.drawAll(playerCamera);
        }
    }

    @Override
    public void input(Time time) {
        cameraController.update();
    }

    public PlayerCamera getCameraController() {
        return cameraController;
    }
}
