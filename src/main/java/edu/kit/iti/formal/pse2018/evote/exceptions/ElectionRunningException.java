package edu.kit.iti.formal.pse2018.evote.exceptions;

public class ElectionRunningException extends Exception {

    public ElectionRunningException(String s) {
        super(s);
    }

    public ElectionRunningException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ElectionRunningException(Throwable throwable) {
        super(throwable);
    }
}
