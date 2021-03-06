package com.helpme.app.game.view.camera;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Time;

import java.util.Observable;

/**
 * Authored by Olle on 2017-05-15.
 */
public abstract class CameraView extends Observable {
    private ICamera camera;
    private Time time;

    public CameraView(ICamera camera, Time time) {
        this.time = time;
        this.camera = camera;
    }

    public ICamera getCamera() {
        return camera;
    }

    public Time getTime() {
        return time;
    }

    public abstract void update();
}
