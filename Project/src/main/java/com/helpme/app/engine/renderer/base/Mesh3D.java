package com.helpme.app.engine.renderer.base;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import com.helpme.app.engine.renderer.Mesh;
import org.lwjgl.system.MemoryUtil;

/**
 * Authored by Olle on 2017-04-04.
 */

public class Mesh3D implements Mesh {
    private int vao; //NOTE(Olle): Vertex3D Array Object (saves drawing settings)
    private int vbo; //NOTE(Olle): Vertex3D Buffer Object (buffer to send vertices to graphics card)
    private int ebo; //NOTE(Olle): Element Buffer Object (buffer to send drawing orders to graphics card)
    private static final int FLOATSIZE = 4;

    private int vertexCount;

    public int getVertexCount() {
        return vertexCount;
    }

    public Mesh3D(Vertex3D[] vertices, int[] indices) {
        vao = glGenVertexArrays();
        vbo = glGenBuffers();
        ebo = glGenBuffers();

        addVertices(vertices, indices);
    }

    private void addVertices(Vertex3D[] vertices, int[] indices) {
        vertexCount = indices.length;

        FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertexCount * Vertex3D.VERTEXSIZE);
        IntBuffer indexBuffer = MemoryUtil.memAllocInt(indices.length);

        for(Vertex3D v : vertices) {
            v.get(vertexBuffer);
        }

        vertexBuffer.flip();
        indexBuffer.put(indices).flip();

        glBindVertexArray(vao);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ebo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, Vertex3D.VERTEXSIZE * FLOATSIZE, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, Vertex3D.VERTEXSIZE * FLOATSIZE, 3 * FLOATSIZE);
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
