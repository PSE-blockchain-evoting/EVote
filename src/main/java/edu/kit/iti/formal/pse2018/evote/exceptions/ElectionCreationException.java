package edu.kit.iti.formal.pse2018.evote.exceptions;

public class ElectionCreationException extends Exception {

    public ElectionCreationException(String s) {
        super(s);
    }

    public ElectionCreationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ElectionCreationException(Throwable throwable) {
        super(throwable);
    }
}
