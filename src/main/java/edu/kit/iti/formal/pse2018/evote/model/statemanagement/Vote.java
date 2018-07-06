package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

public abstract class Vote {
    private String vote;

    public Vote(String vote) {
        this.vote = vote;
    }

    abstract String asString();
}
