package edu.kit.iti.formal.pse2018.evote.exceptions;

public class NetworkConfigException extends Exception {

    public NetworkConfigException(String s) {
        super(s);
    }

    public NetworkConfigException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public NetworkConfigException(Throwable throwable) {
        super(throwable);
    }
}
