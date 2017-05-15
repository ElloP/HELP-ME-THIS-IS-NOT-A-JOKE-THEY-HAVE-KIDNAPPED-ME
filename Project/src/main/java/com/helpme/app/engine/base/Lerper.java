package com.helpme.app.engine.base;

import com.helpme.app.utils.mathl.Vector3f;

/**
 * Authored by Olle on 2017-05-15.
 */

public class Lerper {
    private Vector3f startPoint;
    private Vector3f endPoint;

    private Time time;

    private float currentTime;
    private float endTime = 1f;

    public Lerper(Vector3f startPoint, Vector3f endPoint, Time time) {
        this.startPoint = new Vector3f(startPoint);
        this.endPoint = new Vector3f(endPoint);
        this.time = time;

        this.currentTime = 0f;
    }

    public boolean finished() {
        return currentTime == endTime;
    }

    public Vector3f lerp() {
        currentTime += time.getDeltaTime();
        if(currentTime > endTime) {
            currentTime = endTime; //Note(Olle): clamp to 1 to avoid overshooting
        }

        Vector3f lerpedPoint = new Vector3f();
        startPoint.lerp(endPoint, currentTime, lerpedPoint); //Note(Olle): lerp from startPoint to endPoint into lerpedPoint
        return lerpedPoint;
    }
}
