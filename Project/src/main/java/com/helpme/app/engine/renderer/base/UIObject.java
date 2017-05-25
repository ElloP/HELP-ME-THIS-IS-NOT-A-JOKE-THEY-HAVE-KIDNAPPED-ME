package com.helpme.app.engine.renderer.base;

import com.helpme.app.utils.mathl.Vector2f;

/**
 * Authored by Olle on 2017-05-23.
 */
public class UIObject {
    private Shader shader;
    private Mesh2D mesh;
    private Texture texture;
    private float width;
    private float height;

    Vector2f position;

    public Shader getShader() {
        return shader;
    }

    public UIObject(Texture texture, Vector2f position, float width, float height)
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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        setVertices();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
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
