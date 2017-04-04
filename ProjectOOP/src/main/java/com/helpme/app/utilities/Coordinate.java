package com.helpme.app.utilities;

/**
 * Created by kopa on 2017-03-30.
 */
public class Coordinate {

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate() {

    }

    public int x = 0;
    public int y = 0;

    public Coordinate moveUp() {
        return new Coordinate(x, y+1);
    }

    public Coordinate moveDown() {
        return new Coordinate(x, y-1);
    }

    public Coordinate moveRight() {
        return new Coordinate(x+1, y);
    }

    public Coordinate moveLeft() {
        return new Coordinate(x-1, y);
    }


    @Override
    public int hashCode() {
        String string = "" + x + y;
        return string.hashCode();
    }

    @Override
    public boolean equals(Object o){
        Coordinate other = (Coordinate) o;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }


}
