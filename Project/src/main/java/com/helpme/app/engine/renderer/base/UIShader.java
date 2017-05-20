package com.helpme.app.engine.renderer.base;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.Transform;
import com.helpme.app.engine.base.Window;
import com.helpme.app.utils.mathl.Matrix4f;

/**
 * Created by Jesper on 2017-05-12.
 */
public class UIShader extends Shader {
    private static UIShader uis;

    public static synchronized UIShader getInstance() {
        if(uis == null) {
            uis = new UIShader();
        }
        return uis;
    }

    private UIShader() {
        super("uiVerShader.vs", "fragmentShader.fs");
        addUniform("model");
        //addUniform("view");
        addUniform("projection");
    }

    @Override
    public void updateUniforms(Transform transform, ICamera camera) {
        setUniform("model", transform.getModelMatrix());
        //setUniform("view", camera.getViewMatrix());
        setUniform("projection", transform.getOrthoMatrix(0, Window.getWidth(), Window.getHeight(), 0, -1, 1.0f));
    }

    @Override
    public void updateUniforms(Matrix4f model, Transform transform, ICamera camera) {
        setUniform("model", model);
        setUniform("projection", transform.getOrthoMatrix(0, Window.getWidth(), Window.getHeight(), 0, -1, 1.0f));
    }
}
