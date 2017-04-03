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
        return Direction.values()[(direction.ordinal() + clockwise) % Direction.values().length];
    }
}
