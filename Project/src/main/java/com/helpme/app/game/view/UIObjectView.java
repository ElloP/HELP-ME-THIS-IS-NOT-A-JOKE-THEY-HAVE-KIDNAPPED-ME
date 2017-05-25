package com.helpme.app.game.view;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.UIObject;
import com.helpme.app.utils.mathl.Vector2f;

/**
 * Authored by Jesper on 2017-05-14.
 * Tweaked by Olle on 2017-05-23
 */
public class UIObjectView extends GameObject {
    private UIObject ui;

    public UIObjectView(String texture, Vector2f position, int width, int height) {
        Resources.getTexture(texture).run((t) -> {
            ui = new UIObject(t, position, width, height);
        });
    }

    public void setTexture(String texture) {
        Resources.getTexture(texture).run((t) -> {
            ui.setTexture(t);
        });
    }

    public void setWidth(float i) {
        ui.setWidth(i);
    }

    @Override
    public void draw(ICamera camera) {
        ui.getShader().useProgram();
        ui.getShader().updateUniforms(transform, camera);
        ui.draw();
    }
}
