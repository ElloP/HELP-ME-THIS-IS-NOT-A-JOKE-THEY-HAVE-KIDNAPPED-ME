package com.helpme.app.engine.game.controls;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.Time;

import java.util.Observable;

/**
 * Authored by Olle on 2017-05-15.
 */
public abstract class CameraController extends Observable {
    private ICamera camera;
    private Time time;

    public ICamera getCamera() {
        return camera;
    }

    public Time getTime() {
        return time;
    }

    public CameraController(ICamera camera, Time time) {
        this.camera = camera;
        this.time = time;
    }

    public abstract void update();
}
