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
    }

    public void setClearColor() {
        glClearColor(0.0f,0.0f,0.0f,0.0f);
    }

    public void enableFaceCulling() {
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
    }

    public void disableFaceCulling() {
        glDisable(GL_CULL_FACE);
    }

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

    public void clearWindow() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
