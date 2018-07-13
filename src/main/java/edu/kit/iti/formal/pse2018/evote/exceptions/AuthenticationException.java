package edu.kit.iti.formal.pse2018.evote.exceptions;

public class AuthenticationException extends Exception {

    public AuthenticationException(String s) {
        super(s);
    }

    public AuthenticationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public AuthenticationException(Throwable throwable) {
        super(throwable);
    }
}
