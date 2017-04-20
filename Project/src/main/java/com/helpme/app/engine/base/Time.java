package com.helpme.app.engine.base;

/**
 * Authored by Olle on 2017-04-03.
 */
public class Time {
    public static final long SECOND = 1000000000;

    public static double deltaTime; //deltaTime in seconds

    public static long getTime() {
        return System.nanoTime(); //NOTE(Olle): returns time in nanoseconds
    }

    public static double getTimeInSeconds() {
        return System.nanoTime() / Time.SECOND; //NOTE(Olle): returns time in seconds
    }
}
