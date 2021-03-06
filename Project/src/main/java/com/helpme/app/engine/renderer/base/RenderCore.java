package com.helpme.app.engine.renderer.base;


import com.helpme.app.engine.IRenderCore;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Authored by Olle on 2017-04-04.
 */
public class RenderCore implements IRenderCore {
    private static RenderCore rc;

    public static synchronized RenderCore getRenderCore() {
        if(rc == null) {
            rc = new RenderCore();
        }
        return rc;
    }

    private RenderCore() {
        setClearColor();
        enableFaceCulling();
        enableDepthTest();
        enableTransparentTextures();
    }

    public void setClearColor() {
        glClearColor(0.0f,0.0f,0.0f,0.0f);
    }

    /* Note(Olle): enables face culling, preventing objects turned away from the camera to be rendered
     * as long as the objects vertices are rendered in counter clock-wise order
     */
    public void enableFaceCulling() {
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);

    }

    public void disableFaceCulling() {
        glDisable(GL_CULL_FACE);
    }

    //Note(Olle): enables depth test, which prevent objects behind other objects to be rendered
    public void enableDepthTest() {
        glEnable(GL_DEPTH_TEST);
    }

    public void disableDepthTest() {
        glDisable(GL_DEPTH_TEST);
    }

    public void enableGammaCorrection() {
        glEnable(GL_FRAMEBUFFER_SRGB);
    }

    public void disableGammaCorrection() {
        glDisable(GL_FRAMEBUFFER_SRGB);
    }

    public void enableTransparentTextures() {
        glEnable(GL_BLEND);
        glBlendFunc (GL_SRC_ALPHA,GL_ONE_MINUS_SRC_ALPHA);
    }

    public void disableTransparentTextures() {
        glDisable(GL_BLEND);
    }

    public void clearWindow() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
