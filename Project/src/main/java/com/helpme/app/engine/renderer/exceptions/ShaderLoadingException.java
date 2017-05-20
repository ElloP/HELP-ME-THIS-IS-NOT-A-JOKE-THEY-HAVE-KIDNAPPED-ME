package com.helpme.app.engine.renderer.exceptions;

/**
 * Created by Olle on 2017-05-20.
 */
public class ShaderLoadingException extends RuntimeException {
    public ShaderLoadingException() {
        super();
    }

    public ShaderLoadingException(String message) {
        super(message);
    }

    public ShaderLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
    public ShaderLoadingException(String message, Throwable cause, boolean enableSuppresion, boolean writeableStackTrace) {
        super(message, cause, enableSuppresion, writeableStackTrace);
    }
    public ShaderLoadingException(Throwable cause) {
        super(cause);
    }
}
