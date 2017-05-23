package com.helpme.app.engine.renderer.base;

import com.helpme.app.engine.renderer.Mesh;
import com.helpme.app.utils.Vector2f;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Authored by Olle on 2017-05-23.
 */
public class Mesh2D implements Mesh {
    private int vao; //NOTE(Olle): Vertex2D Array Object (saves drawing settings)
    private int vbo; //NOTE(Olle): Vertex2D Buffer Object (buffer to send vertices to graphics card)
    private static final int FLOATSIZE = 4;

    private int vertexCount;

    public int getVertexCount() {
        return vertexCount;
    }

    public int getVao() {
        return vao;
    }


    public Mesh2D(Vertex2D[] vertices) {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();

        addVertices(vertices);
    }

    private void addVertices(Vertex2D[] vertices) {
        vertexCount = vertices.length;

        FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertexCount * 4);

        for(Vertex2D v : vertices) {
            v.get(vertexBuffer);
        }

        vertexBuffer.flip();

        glBindVertexArray(vao);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        glBindVertexArray(vao);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, Vertex2D.VERTEXSIZE * FLOATSIZE, 0);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex2D.VERTEXSIZE * FLOATSIZE, 2 * FLOATSIZE);
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);

        MemoryUtil.memFree(vertexBuffer);
    }

    public void draw() {
        glBindVertexArray(vao);

        glDrawArrays(GL_TRIANGLES, 0, vertexCount);

        glBindVertexArray(0);
    }

    public void destroy() {
        glDisableVertexAttribArray(0);

        glDeleteBuffers(vbo);

        glBindVertexArray(0);
        glDeleteVertexArrays(vao);
    }
}
