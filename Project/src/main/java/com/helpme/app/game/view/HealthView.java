package com.helpme.app.game.view;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.utils.mathl.Vector2f;

/**
 * Created by Olle on 2017-05-24.
 */
public class HealthView extends GameObject {
    private UIObjectView frame;
    private UIObjectView health;

    private final int WIDTH = 350;
    private final int HEIGHT = 60;

    private Vector2f position;

    private final int FRAME_PADDING_X = 9;
    private final int FRAME_PADDING_Y = 11;

    public HealthView(Vector2f position) {
        this.position = position;

        frame = new UIObjectView("healthframe", position, WIDTH, HEIGHT);
        Vector2f barPos = new Vector2f(position.x + FRAME_PADDING_X, position.y + FRAME_PADDING_Y);
        health = new UIObjectView("healthbar", barPos, WIDTH - FRAME_PADDING_X * 2, HEIGHT - FRAME_PADDING_Y * 2);

        addChild(health);
        addChild(frame);
    }

    public void setHealth(int healthAmt) {
        if(healthAmt > 100) {
            healthAmt = 100;
        } else if (healthAmt < 0) {
            healthAmt = 0;
        }
        float widthPercentage = ((float) healthAmt) / 100;
        float newWidth = widthPercentage * (WIDTH - FRAME_PADDING_X *2);
        health.setWidth(newWidth);
    }


    @Override
    public void draw(ICamera camera) {
    }
}
