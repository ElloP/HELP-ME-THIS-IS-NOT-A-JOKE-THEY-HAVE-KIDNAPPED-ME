package com.helpme.app.engine.renderer.base;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.Transform;
import com.helpme.app.engine.base.Window;

/**
 * Created by Olle on 2017-04-21.
 */
public class DefaultShader extends Shader {
    private static DefaultShader defaultShader;

    public static DefaultShader getDefaultShader() {
        if (defaultShader == null) {
            defaultShader = new DefaultShader();
        }
        return defaultShader;
    }

    private DefaultShader() {
        super("vertexShader.vs", "fragmentShader.fs");
        addUniform("model");
        addUniform("view");
        addUniform("projection");
    }

    public void updateUniforms(Transform transform, Camera camera)
    {
        setUniform("model", transform.getModelMatrix());
        setUniform("view", camera.getViewMatrix());
        setUniform("projection", transform.getPerspectiveMatrix(70f, Window.width, Window.height, 0.1f, 1000));
    }
}
