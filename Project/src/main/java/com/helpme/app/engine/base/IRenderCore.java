package com.helpme.app.engine.base;

/**
 * Authored by Olle on 2017-05-18.
 */
public interface IRenderCore {
    void setClearColor();

    void enableFaceCulling();

    void disableFaceCulling();

    void enableDepthTest();

    void disableDepthTest();

    void enableGammaCorrection();

    void disableGammaCorrection();

    void clearWindow();
}
