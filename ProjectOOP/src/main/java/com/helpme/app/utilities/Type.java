package com.helpme.app.utilities;

/**
 * Created by kopa on 2017-04-03.
 */
public class Type {
    private Type() {

    }

    public enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    public static Direction rotateDirection(Direction direction, int clockwise) {
        int directionIndex = Math.floorMod(direction.ordinal() + clockwise, Direction.values().length);
        return Direction.values()[directionIndex];
    }
}
