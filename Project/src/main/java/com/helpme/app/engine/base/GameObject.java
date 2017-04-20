package com.helpme.app.engine.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olle on 2017-04-20.
 */
public class GameObject {
    public Transform transform;

    private List<GameObject> children;

    public GameObject() {
        children = new ArrayList<GameObject>();
    }

    public GameObject(ArrayList<GameObject> children) {
        this.children = children;
    }

    public void addChild(GameObject child) {
        children.add(child);
        child.transform = this.transform;
    }
}
