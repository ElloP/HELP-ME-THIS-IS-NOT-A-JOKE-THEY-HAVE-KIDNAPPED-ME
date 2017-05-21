package com.helpme.app.engine.renderer.exceptions;

/**
 * Created by Olle on 2017-05-20.
 */
public class ShaderLinkingException extends RuntimeException{
    public ShaderLinkingException() {
        super();
    }

    public ShaderLinkingException(String message) {
        super(message);
    }

    public ShaderLinkingException(String message, Throwable cause) {
        super(message, cause);
    }
    public ShaderLinkingException(String message, Throwable cause, boolean enableSuppresion, boolean writeableStackTrace) {
        super(message, cause, enableSuppresion, writeableStackTrace);
    }
    public ShaderLinkingException(Throwable cause) {
        super(cause);
    }
}
