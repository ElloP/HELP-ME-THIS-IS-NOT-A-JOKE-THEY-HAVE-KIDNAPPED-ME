package com.helpme.app.engine.base;

/**
 * Authored by Olle on 2017-04-05.
 */
public abstract class Game {
    public Camera activeCamera;
    public Scene scene; //Note(Olle): Every gameobject in the scene will have this as a root object

    public Game() {
        scene = new Scene();
    }

    public abstract void input();

    public abstract void update();

    public void draw() {
        scene.draw(activeCamera);
    }
}
