package com.helpme.app.engine.base;

/**
 * Authored by Olle on 2017-05-02.
 */
public class Scene extends GameObject {
    @Override
    public void draw(Camera camera) {
        drawScene(camera);
    }

    private void drawScene(Camera camera) {
        for(GameObject child : children) {
            child.drawAll(camera);
        }
    }
}
