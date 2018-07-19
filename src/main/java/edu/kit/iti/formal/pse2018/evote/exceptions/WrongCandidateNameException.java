package edu.kit.iti.formal.pse2018.evote.exceptions;

public class WrongCandidateNameException extends Exception {

    public WrongCandidateNameException(String s) {
        super(s);
    }

    public WrongCandidateNameException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public WrongCandidateNameException(Throwable throwable) {
        super(throwable);
    }
}
