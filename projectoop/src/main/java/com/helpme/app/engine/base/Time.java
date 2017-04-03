package com.helpme.app.engine.base;

/**
 * Created by Olle on 2017-04-03.
 */
public class Time {
    public static final long SECOND = 1000000000;

    public static long deltaTime;

    public static long getTime() {
        return System.nanoTime();
    }
}
