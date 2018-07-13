package edu.kit.iti.formal.pse2018.evote.exceptions;

/**
 * Unrecoverable exception that originated in the SDK.
 */
public class InternalSDKException extends Exception {

    public InternalSDKException(String s) {
        super(s);
    }

    public InternalSDKException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public InternalSDKException(Throwable throwable) {
        super(throwable);
    }
}
