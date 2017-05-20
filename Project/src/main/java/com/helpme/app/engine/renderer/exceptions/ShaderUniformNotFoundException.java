package com.helpme.app.engine.renderer.exceptions;

/**
 * Created by Olle on 2017-05-20.
 */
public class ShaderUniformNotFoundException extends RuntimeException {
    public ShaderUniformNotFoundException() {
        super();
    }

    public ShaderUniformNotFoundException(String message) {
        super(message);
    }

    public ShaderUniformNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShaderUniformNotFoundException(String message, Throwable cause, boolean enableSuppresion, boolean writeableStackTrace) {
        super(message, cause, enableSuppresion, writeableStackTrace);
    }

    public ShaderUniformNotFoundException(Throwable cause) {
        super(cause);
    }
}