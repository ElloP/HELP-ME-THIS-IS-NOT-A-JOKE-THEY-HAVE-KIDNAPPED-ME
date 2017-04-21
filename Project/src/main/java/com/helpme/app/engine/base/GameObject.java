package com.helpme.app.engine.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Authored by Olle on 2017-04-20.
 */
public class GameObject {
    public Transform transform;

    private List<GameObject> children;

    public GameObject() {
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
}
