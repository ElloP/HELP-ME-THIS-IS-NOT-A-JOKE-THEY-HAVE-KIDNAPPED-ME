package com.helpme.app.engine.game;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.Mesh;
import com.helpme.app.engine.renderer.base.Shader;
import com.helpme.app.engine.renderer.base.Texture;
import com.helpme.app.engine.renderer.base.UIShader;
import com.helpme.app.utils.Vector2f;
import com.helpme.app.utils.mathl.Vector3f;
import org.lwjgl.opengl.GL13;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 * Created by Jesper on 2017-05-14.
 */
public class UIController extends GameObject{
    private Shader shader;
    private Texture texture;
    private Mesh mesh;
    private Vector2f model;
    private Vector2f texCoords;
    public UIController(String texture) {
        /*float[] positions = {-1, 1, -1, -1, 1, 1, 1, -1};
        this.transform.setPosition(new Vector3f(0, 0, 0));*/
        //this.shader = new UIShader();
        Resources.getTexture(texture).run(t -> {
            this.texture = t;
        });
        this.shader = new UIShader();
        this.mesh = new Mesh(Resources.uiVert2());
        transform.scale(200, 100, 1);
        /*Resources.getTexture(texture).run(t -> {
            Quad floor = new Quad(t, new UIShader(), Resources.floor(), Resources.uiVert2());
            addChild(floor);
        });*/
        this.transform.setPosition(1200, 200, 0);

    }



    @Override
    public void draw(Camera camera) {
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
