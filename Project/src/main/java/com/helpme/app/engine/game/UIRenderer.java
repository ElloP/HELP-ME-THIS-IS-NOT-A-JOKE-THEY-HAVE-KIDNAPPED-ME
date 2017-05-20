package com.helpme.app.engine.game;

import com.helpme.app.engine.ICamera;
import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.Mesh;
import com.helpme.app.engine.renderer.base.Shader;
import com.helpme.app.engine.renderer.base.Texture;
import com.helpme.app.engine.renderer.base.UIShader;
import com.helpme.app.utils.Vector2f;
import org.lwjgl.opengl.GL13;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 * Created by Jesper on 2017-05-14.
 */
public class UIRenderer extends GameObject{
    private Shader shader;
    private Texture texture;
    private Mesh mesh;
    private int width;
    private int height;
    public UIRenderer(String texture, Vector2f position, Vector2f scale) {
        Resources.getTexture(texture).run(t -> {
            this.texture = t;
            this.width = t.getWidth();
            this.height = t.getHeight();
        });
        this.mesh = new Mesh(Resources.uiVert(width, height));
        this.shader = new UIShader();
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
        //transform.translate(0.5f * transform.getPosition().x(), 0.5f * transform.getPosition().y(), 0);
        //transform.rotate(0, 0, 1);
        //transform.translate(-0.5f * transform.getPosition().x(), -0.5f * transform.getPosition().y(), 0);
        //transform.translate(camera.getPosition().add(camera.getForward()));
        shader.updateUniforms(transform, camera);
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        texture.bind();
        glBindVertexArray(mesh.vao);
        glDrawArrays(GL_TRIANGLES, 0, 6);
        glBindVertexArray(0);
        texture.unBind();
    }
}
