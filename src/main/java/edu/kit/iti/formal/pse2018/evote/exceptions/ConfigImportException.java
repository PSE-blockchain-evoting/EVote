package edu.kit.iti.formal.pse2018.evote.exceptions;

public class ConfigImportException extends Exception {

    public ConfigImportException(String s) {
        super(s);
    }

    public ConfigImportException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ConfigImportException(Throwable throwable) {
        super(throwable);
    }
}
