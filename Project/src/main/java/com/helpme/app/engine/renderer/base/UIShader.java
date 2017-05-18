package com.helpme.app.engine.renderer.base;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.Transform;
import com.helpme.app.engine.base.Window;

/**
 * Created by Jesper on 2017-05-12.
 */
public class UIShader extends Shader {

    public UIShader() {
        super("uiVerShader.vs", "fragmentShader.fs");
        addUniform("model");
        //addUniform("view");
        addUniform("projection");
    }

    @Override
    public void updateUniforms(Transform transform, Camera camera) {
        setUniform("model", transform.getModelMatrix());
        //setUniform("view", camera.getViewMatrix());
        setUniform("projection", transform.getOrthoMatrix(0, Window.width, Window.height, 0, -1, 1.0f));
    }
}
