package edu.kit.iti.formal.pse2018.evote.exceptions;

public class LoadVoteException extends Exception {

    public LoadVoteException(String s) {
        super(s);
    }

    public LoadVoteException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public LoadVoteException(Throwable throwable) {
        super(throwable);
    }
}
