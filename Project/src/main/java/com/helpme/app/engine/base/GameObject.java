package com.helpme.app.engine.base;

import com.helpme.app.engine.ICamera;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Authored by Olle on 2017-04-20.
 */

    /* Note(Olle): game objects are objects that can be drawn onto the screen or act as a folder
     * (or group) for other game objects enabling the engine user to use the same transform for multiple objects
     * and to draw objects in groups
     */
public abstract class GameObject extends Observable {
    public Transform transform;

    protected List<GameObject> children;

    protected boolean active = true;

    public GameObject() {
        transform = new Transform();
        children = new ArrayList<GameObject>();
    }

    public GameObject(ArrayList<GameObject> children) {
        for(GameObject child : children) {
            addChild(child);
        }
    }

    public void addChild(GameObject child) {
        children.add(child);
        child.transform.setParent(this.transform);
    }

    public abstract void draw(ICamera camera);

    public void drawAll(ICamera camera) {
        draw(camera);

        for(GameObject child : children) {
            child.drawAll(camera);
        }
    }

    public boolean isActive() {
        return active;
    }
}
