package com.helpme.app.engine.renderer.base;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.Transform;
import com.helpme.app.engine.base.Window;
import com.helpme.app.utils.mathl.Matrix4f;

/**
 * Authored by Olle on 2017-04-21.
 */

//Note(Olle): the default shader, with the default variables
public class DefaultShader extends Shader {

    private static DefaultShader ds;

    public static synchronized DefaultShader getDefaultShader() {
        if(ds == null) {
            ds = new DefaultShader();
        }
        return ds;
    }

    private DefaultShader() {
        super("vertexShader.vs", "fragmentShader.fs");
        addUniform("model");
        addUniform("view");
        addUniform("projection");
    }

    public void updateUniforms(Transform transform, ICamera camera)
    {
        setUniform("model", transform.getModelMatrix());
        setUniform("view", camera.getViewMatrix());
        setUniform("projection", Transform.getPerspectiveMatrix(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000));
    }

    public void updateUniforms(Matrix4f model, Transform transform, ICamera camera)
    {
        setUniform("model", model);
        setUniform("view", camera.getViewMatrix());
        setUniform("projection", Transform.getPerspectiveMatrix(70f, Window.getWidth(), Window.getHeight(), 0.1f, 1000));
    }
}
