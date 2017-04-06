package com.helpme.app;

import com.helpme.app.utils.Vector2f;

/**
 * Co-authored by Klas, Jacob, Olle and Jesper
 */

public class App {
    public void run(){
        Vector2f vec = new Vector2f(0,1);
        System.out.println(vec.x + ", " + vec.y);
        vec = vec.rotateRightAngle(-1);
        System.out.println(vec.x + ", " + vec.y);
    }

    public static void main(String[] args) {
        new App().run();
    }
}
