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

    public void enableMipMapping() {
        mipMapping = true;
    }

    public void disableMipMapping() {
        mipMapping = false;
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    public static void unBind() {
        glBindTexture(GL_TEXTURE_2D,0);
    }

    void generate(ByteBuffer image, int width, int height) {
        id = glGenTextures();

        bind();
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB8, width, height ,0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, minFilter);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, maxFilter);
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
}
