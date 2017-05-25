package com.helpme.app.engine.base;

import com.helpme.app.engine.ICamera;

/**
 * Authored by Olle on 2017-05-02.
 */
public abstract class Scene extends GameObject {
    @Override
    public void draw(ICamera camera) {
        drawScene(camera);
    }

    private void drawScene(ICamera camera) {
        for(GameObject child : children) {
            child.drawAll(camera);
        }
    }

    public void input(Time time) {

    }
}
