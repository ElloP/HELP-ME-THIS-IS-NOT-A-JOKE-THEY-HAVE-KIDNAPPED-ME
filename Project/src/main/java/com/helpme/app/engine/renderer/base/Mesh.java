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

    public Mesh(Vertex[] vertices, int[] indices) {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        ebo = glGenBuffers();

        addVertices(vertices, indices);
    }

    public void addVertices(Vertex[] vertices, int[] indices) {
        final int FLOATSIZE = 4; //in bytes
        vertexCount = indices.length;

        FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertexCount * Vertex.VERTEXSIZE);
        IntBuffer indexBuffer = MemoryUtil.memAllocInt(indices.length);

        for(Vertex v : vertices) {
            v.get(vertexBuffer);
        }

        vertexBuffer.flip();
        indexBuffer.put(indices).flip();

        glBindVertexArray(vao);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex.VERTEXSIZE * FLOATSIZE, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex.VERTEXSIZE * FLOATSIZE, 3 * FLOATSIZE);
        glEnableVertexAttribArray(1);

        glBindVertexArray(0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);

        MemoryUtil.memFree(vertexBuffer);
        MemoryUtil.memFree(indexBuffer);
    }

    public void draw() {
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
