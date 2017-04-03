package com.helpme.app.utilities;

/**
 * Created by kopa on 2017-03-30.
 */
public class Coordinate {
    public int x = 0;
    public int y = 0;

    public void moveUp() {
        y++;
    }

    public void moveDown() {
        y--;
    }

    public void moveRight() {
        x++;
    }

    public void moveLeft() {
        x--;
    }


    public int hashCode() {
        String string = "" + x + y;
        return string.hashCode();
    }

}
