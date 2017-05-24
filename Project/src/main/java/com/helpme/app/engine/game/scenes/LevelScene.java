package com.helpme.app.engine.game.scenes;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.base.Scene;
import com.helpme.app.engine.base.Time;
import com.helpme.app.engine.game.LevelController;
import com.helpme.app.engine.game.NPCView;
import com.helpme.app.engine.game.UIRenderer;
import com.helpme.app.engine.game.controls.CameraController;
import com.helpme.app.engine.game.controls.PlayerCamera;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Vector3f;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.level.ILevel;

/**
 * Created by Jesper on 2017-05-22.
 */
public class LevelScene extends Scene {
    private UIRenderer health;
    private ICamera playerCamera;

    private PlayerCamera cameraController;

    //TODO (Jesper): Should accept a player to read hp?
    public LevelScene(ILevel level, IReadBody player, Time time) {
        addChild(new LevelController(level));
        for (IReadBody body : level.readBodies()) {
            addChild(new NPCView(body.readPosition().x, body.readPosition().y));
        }
        System.out.println(player.readDirection());
        this.playerCamera = new Camera(player.readPosition(), player.readDirection());

        health = new UIRenderer("", new Vector2f(1300, 800), 2);

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
