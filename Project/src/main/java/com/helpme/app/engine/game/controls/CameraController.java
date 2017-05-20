package com.helpme.app.engine.game.controls;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.Time;

import java.util.Observable;

/**
 * Authored by Olle on 2017-05-15.
 */
public abstract class CameraController extends Observable {
    private Camera camera;
    private Time time;

    public Camera getCamera() {
        return camera;
    }

    public Time getTime() {
        return time;
    }

    public CameraController(Camera camera, Time time) {
        this.camera = camera;
        this.time = time;
    }

    public abstract void update();
}
