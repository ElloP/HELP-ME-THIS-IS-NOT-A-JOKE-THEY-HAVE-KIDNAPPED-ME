package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.*;
import com.helpme.app.utils.Vector2f;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 * Created by Jesper on 2017-05-14.
 */
public class UIRenderer extends GameObject {
    private Shader shader;
    private Texture texture;
    private Mesh2D mesh2D;
    private int width;
    private int height;
    public UIRenderer(String texture, Vector2f position, Vector2f scale) {
        Resources.getTexture(texture).run(t -> {
            this.texture = t;
            this.width = t.getWidth();
            this.height = t.getHeight();
        });
        this.mesh2D = new Mesh2D(Resources.uiVert(width, height));
        this.shader = UIShader.getInstance();
        this.transform.scale(scale.x, scale.y, 1);
        this.transform.setPosition(position.x, position.y, 0);
        //Note (Jesper): If faceculling is enabled the transform has to be rotated. Otherwise it doesn't matter.
        this.transform.rotate(180, 0, 0);

    }

    public UIRenderer(String texture, Vector2f position, float scale) {
        this(texture, position, new Vector2f(scale, scale));
    }

    public void setTexture (String texture) {
        Resources.getTexture(texture).run(t -> {
            this.texture = t;
        });
    }


    @Override
    public void draw(ICamera camera) {
        shader.useProgram();
        shader.updateUniforms(transform, camera);
        texture.bind();
        mesh2D.draw();
        texture.unBind();
    }
}
