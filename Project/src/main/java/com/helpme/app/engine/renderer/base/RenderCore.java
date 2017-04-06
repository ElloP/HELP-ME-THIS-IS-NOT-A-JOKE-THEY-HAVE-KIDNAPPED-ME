package com.helpme.app.engine.renderer.base;


import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Created by Olle on 2017-04-04.
 */
public class RenderCore {

    public static void init() {
        setClearColor();
        enableFaceCulling();
        enableDepthTest();
        enableGammaCorrection();
    }

    public static void setClearColor() {
        glClearColor(0.0f,0.0f,0.0f,0.0f);
    }

    public static void enableFaceCulling() {
        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
    }

    public static void disableFaceCulling() {
        glDisable(GL_CULL_FACE);
    }

    public static void enableDepthTest() {
        glEnable(GL_DEPTH_TEST);
    }

    public static void disableDepthTest() {
        glDisable(GL_DEPTH_TEST);
    }

    public static void enableGammaCorrection() {
        glEnable(GL_FRAMEBUFFER_SRGB);
    }

    public static void disableGammaCorrection() {
        glDisable(GL_FRAMEBUFFER_SRGB);
    }

    public static void clearWindow() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }
}
