package com.helpme.app.engine.renderer.exceptions;

/**
 * Created by Olle on 2017-05-20.
 */
public class ShaderCreationException extends RuntimeException{

    public ShaderCreationException() {
        super();
    }

    public ShaderCreationException(String message) {
        super(message);
    }

    public ShaderCreationException(String message, Throwable cause) {
        super(message, cause);
    }
    public ShaderCreationException(String message, Throwable cause, boolean enableSuppresion, boolean writeableStackTrace) {
        super(message, cause, enableSuppresion, writeableStackTrace);
    }
    public ShaderCreationException(Throwable cause) {
        super(cause);
    }
}
