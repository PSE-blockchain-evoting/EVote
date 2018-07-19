package edu.kit.iti.formal.pse2018.evote.exceptions;

public class FailedDetermineWinnerException extends Exception {

    public FailedDetermineWinnerException(String s) {
        super(s);
    }

    public FailedDetermineWinnerException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public FailedDetermineWinnerException(Throwable throwable) {
        super(throwable);
    }
}
