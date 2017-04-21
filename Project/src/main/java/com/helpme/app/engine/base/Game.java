package com.helpme.app.engine.base;

import com.helpme.app.engine.input.Input;
import com.helpme.app.engine.input.InputKey;
import com.helpme.app.engine.input.KeyState;
import com.helpme.app.engine.renderer.base.*;
import com.helpme.app.engine.utils.TextureLoader;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Matrix4f;
import com.helpme.app.utils.mathl.Quaternion;

/**
 * Authored by Olle on 2017-04-05.
 */
public abstract class Game {
    //TODO(Olle): set up logic for updating the scene's gameobjects
    private GameObject Scene; //Note(Olle): Every gameobject in the scene will have this as a root object

    public void input() {}

    public void update() {}

    public void draw() {}
}
