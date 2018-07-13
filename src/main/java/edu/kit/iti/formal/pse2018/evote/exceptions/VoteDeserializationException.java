package edu.kit.iti.formal.pse2018.evote.exceptions;

public class VoteDeserializationException extends Exception {

    public VoteDeserializationException(String s) {
        super(s);
    }

    public VoteDeserializationException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public VoteDeserializationException(Throwable throwable) {
        super(throwable);
    }
}
