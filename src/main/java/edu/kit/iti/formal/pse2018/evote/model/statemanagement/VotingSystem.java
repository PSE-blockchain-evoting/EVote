package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;

public abstract class VotingSystem {
    protected Election election;

    public VotingSystem(Election election) {
        this.election = election;
    }

    abstract int[] determineResults() throws NetworkException, NetworkConfigException;

    abstract Vote loadVote(String vote);

    abstract Candidate determineWinner() throws NetworkException, NetworkConfigException;
}
