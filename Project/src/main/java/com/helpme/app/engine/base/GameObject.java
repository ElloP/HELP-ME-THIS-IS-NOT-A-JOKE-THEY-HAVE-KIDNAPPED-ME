package com.helpme.app.engine.base;

import com.helpme.app.engine.ICamera;

import java.util.ArrayList;
import java.util.List;

/**
 * Authored by Olle on 2017-04-20.
 */
public abstract class GameObject {
    public Transform transform;

    public List<GameObject> children;

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
}
