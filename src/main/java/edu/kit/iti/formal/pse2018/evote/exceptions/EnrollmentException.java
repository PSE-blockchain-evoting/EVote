package edu.kit.iti.formal.pse2018.evote.exceptions;

public class EnrollmentException extends Exception {

    public EnrollmentException(String s) {
        super(s);
    }

    public EnrollmentException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public EnrollmentException(Throwable throwable) {
        super(throwable);
    }
}
