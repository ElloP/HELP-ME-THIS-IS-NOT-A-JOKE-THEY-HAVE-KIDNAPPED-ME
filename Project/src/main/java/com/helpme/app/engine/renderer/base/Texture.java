package com.helpme.app.engine.renderer.base;

import java.nio.ByteBuffer;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Authored by Olle on 2017-04-18.
 */
public class Texture {
    private int id;

    private boolean mipMapping = true;

    private int maxFilter = GL_LINEAR;
    private int minFilter = GL_LINEAR_MIPMAP_LINEAR;
    private int wrapS = GL_REPEAT;
    private int wrapT = GL_REPEAT;

    private int width;
    private  int height;

    //Note(Olle): enables mipmapping, causing textures further away from the camera to be rendered as smaller images
    public void enableMipMapping() {
        mipMapping = true;
    }

    public void disableMipMapping() {
        mipMapping = false;
    }

    //Note(Olle): activates the current texture for rendering
    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public void unBind() {
        glBindTexture(GL_TEXTURE_2D,0);
    }

    void generate(ByteBuffer image, int width, int height) {
        id = glGenTextures();
        this.width = width;
        this.height = height;

        bind();
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height ,0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            //Note(Olle): setting the magnification filters
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, minFilter);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, maxFilter);
            //Note(Olle): setting the texture wrapping settings
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, wrapS);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, wrapT);
            if(mipMapping) {
                glGenerateMipmap(GL_TEXTURE_2D);
            }
        unBind();
    }

    public int getID() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
