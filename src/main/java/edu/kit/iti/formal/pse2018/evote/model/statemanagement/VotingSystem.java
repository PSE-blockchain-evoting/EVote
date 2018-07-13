package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

public abstract class VotingSystem {
    private Election election;

    public VotingSystem(Election election) {
        this.election = election;
    }

    public int[] determineResults() {
        //TODO: ACHIM FRAGEN WAS HIER PASSIEREN SOLL
        return null;
    }

    abstract Vote loadVote(String vote);

    abstract Candidate determineWinner();
}
