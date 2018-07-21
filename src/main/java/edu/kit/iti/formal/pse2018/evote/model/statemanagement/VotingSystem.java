package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

public abstract class VotingSystem {
    protected Election election;

    public VotingSystem(Election election) {
        this.election = election;
    }

    abstract int[] determineResults();

    abstract Vote loadVote(String vote);

    abstract String determineWinner();
}
