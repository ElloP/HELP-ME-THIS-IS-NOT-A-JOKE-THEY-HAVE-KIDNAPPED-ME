package com.helpme.app.engine.base;

/**
 * Authored by Olle on 2017-04-03.
 */
public class Time {
    public static final long SECOND = 1000000000;

    public static double deltaTime;

    public static double getTimeInSeconds() {
        return System.nanoTime() / Time.SECOND; //NOTE(Olle): returns time in seconds
    }

    public static long getTime() {
        return System.nanoTime();
    }
}
