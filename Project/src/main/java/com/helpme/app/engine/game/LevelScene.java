package com.helpme.app.engine.game;

import com.helpme.app.engine.base.Scene;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.world.body.IReadBody;
import com.helpme.app.world.level.ILevel;

/**
 * Created by Jesper on 2017-05-22.
 */
public class LevelScene extends Scene {
    private UIRenderer health;

    //TODO (Jesper): Should accept a player to read hp?
    public LevelScene(ILevel level) {
        addChild(new LevelController(level));
        for (IReadBody body : level.readBodies()) {
            addChild(new NPCView(body.readPosition().x, body.readPosition().y));
        }

        health = new UIRenderer("", new Vector2f(1300, 800), 2);

    }

    public void updateHealth(int newHealth) {
        if (newHealth > 80) {
            health.setTexture("health");
        } else if (newHealth <= 80 && newHealth > 60) {
            health.setTexture("health80");
        }
    }
}
