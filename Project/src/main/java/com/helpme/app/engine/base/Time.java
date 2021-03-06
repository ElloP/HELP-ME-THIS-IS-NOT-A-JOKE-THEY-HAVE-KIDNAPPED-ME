package com.helpme.app.engine.base;

/**
 * Authored by Olle on 2017-04-03.
 */

//Note(Olle): time class to keep track of deltas and other time related matters needed for the engine
public class Time {
    // ----------- Time variables -----------
    public static final double SECOND = 1000000000;

    private long startTime;
    protected double deltaTime; //deltaTime in seconds

    // ----------- Time constructors and getters -----------
    public Time() {
        startTime = System.nanoTime();
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public long getTime() {
        return System.nanoTime() - startTime; //NOTE(Olle): returns time in nanoseconds
    }

    public double getTimeInSeconds() {
        return System.nanoTime() / Time.SECOND; //NOTE(Olle): returns time in seconds
    }
}
