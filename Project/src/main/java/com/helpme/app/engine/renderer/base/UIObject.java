package com.helpme.app.engine.renderer.base;

import com.helpme.app.utils.Vector2f;

/**
 * Authored by Olle on 2017-05-23.
 */
public class UIObject {
    private Shader shader;
    private Mesh2D mesh;
    private Texture texture;
    private int width;
    private int height;

    Vector2f position;

    public Shader getShader() {
        return shader;
    }

    public UIObject(Texture texture, Vector2f position,  int width, int height)
    {
        this.texture = texture;
        shader = UIShader.getInstance();
        this.width = width;
        this.height = height;
        this.position = position;

        setVertices();
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        setVertices();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        setVertices();
    }

    private void setVertices() {
        Vertex2D[] vertices = {
                new Vertex2D(position.x, position.y + height, 0, 1),
                new Vertex2D(position.x, position.y, 0, 0),
                new Vertex2D(position.x + width, position.y, 1, 0),
                new Vertex2D(position.x, position.y + height, 0, 1),
                new Vertex2D(position.x + width, position.y, 1, 0),
                new Vertex2D(position.x + width, position.y + height, 1, 1)
        };
        mesh = new Mesh2D(vertices);
    }

    public void draw() {
        texture.bind();
        mesh.draw();
        texture.unBind();
    }
}
