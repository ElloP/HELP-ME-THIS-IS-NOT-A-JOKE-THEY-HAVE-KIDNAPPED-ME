package com.helpme.app.engine.game;

import com.helpme.app.engine.base.Camera;
import com.helpme.app.engine.base.GameObject;
import com.helpme.app.engine.renderer.base.Shader;
import com.helpme.app.engine.renderer.base.Texture;
import com.helpme.app.utils.Vector2f;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

/**
 * Created by Jesper on 2017-05-14.
 */
public class UIObject extends GameObject {
    private Texture texture;
    private Vector2f position;
    private Vector2f scale;
    private Shader shader;

    public UIObject(String texture, Vector2f position, Vector2f scale) {
        Resources.getTexture(texture).run(t -> {
            this.texture = t;
        });
        this.position = position;
        this.scale = scale;
    }

    @Override
    public void draw(Camera camera) {
        shader.updateUniforms(transform, camera);
        texture.bind();
        shader.useProgram();
        //glBindVertexArray(vao);

        //glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
        texture.unBind();
    }
}
