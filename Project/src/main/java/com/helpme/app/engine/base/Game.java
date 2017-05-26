package com.helpme.app.engine.base;

import com.helpme.app.engine.ICamera;

/**
 * Authored by Olle on 2017-04-05.
 */
public abstract class Game {
    protected ICamera activeCamera;
    protected Scene scene; //Note(Olle): Every gameobject in the scene will have this as a root object

    public Game(Scene scene, ICamera camera) {
        this.scene = scene;
        this.activeCamera = camera;
    }

    public abstract void stop();

    public abstract void input(Time time);

    public abstract void update(Time time);

    public void setActiveScene(Scene newScene){
        this.scene = newScene;
    }

    public void draw() {
        scene.draw(activeCamera);
    }
}
