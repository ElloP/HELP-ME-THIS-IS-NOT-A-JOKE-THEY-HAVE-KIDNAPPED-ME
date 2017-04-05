package com.helpme.app.engine.renderer.base;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import org.lwjgl.system.MemoryUtil;

/**
 * Authored by Olle on 2017-04-04.
 */

public class Mesh {
    private int vao; //NOTE(Olle): Vertex Array Object (saves drawing settings)
    private int vbo; //NOTE(Olle): Vertex Buffer Object (buffer to send vertices to graphics card)
    private int ebo; //NOTE(Olle): Element Buffer Object (buffer to send drawing orders to graphics card)

    private int vertexCount;

    public int getVertexCount() {
        return vertexCount;
    }

    public Mesh(float[] vertices, int[] indices) {
        FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertices.length);
        IntBuffer indexBuffer = MemoryUtil.memAllocInt(indices.length);

        final int FLOATSIZE = 4; //in bytes
        final int VERTEXSIZE = 3; //number of float in one vertex

        vertexCount = indices.length;

        vertexBuffer.put(vertices).flip();
        indexBuffer.put(indices).flip();

        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        ebo = glGenBuffers();

        glBindVertexArray(vao);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, VERTEXSIZE * FLOATSIZE, 0);
        glEnableVertexAttribArray(0);

        glBindVertexArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        MemoryUtil.memFree(vertexBuffer);
        MemoryUtil.memFree(indexBuffer);
    }

    public void draw() { //TODO(Olle): find a better place to draw mesh
        glBindVertexArray(vao);

        glDrawElements(GL_TRIANGLES, vertexCount, GL_UNSIGNED_INT, 0);

        glBindVertexArray(0);
    }

    public void destroy() {
        glDisableVertexAttribArray(0);

        glDeleteBuffers(vbo);
        glDeleteBuffers(ebo);

        glBindVertexArray(0);
        glDeleteVertexArrays(vao);
    }
}
