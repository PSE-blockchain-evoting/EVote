package edu.kit.iti.formal.pse2018.evote.exceptions;

public class NetworkException extends Exception {

    public NetworkException(String s) {
        super(s);
    }

    public NetworkException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NetworkException(Throwable throwable) {
        super(throwable);
    }
}
