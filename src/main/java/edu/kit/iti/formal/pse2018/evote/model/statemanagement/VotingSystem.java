package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.LoadVoteException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;

public abstract class VotingSystem {
    protected Election election;

    public VotingSystem(Election election) {
        this.election = election;
    }

    abstract int[] determineResults();

    abstract void loadVote(String vote) throws LoadVoteException, WrongCandidateNameException;

    abstract String determineWinner();
}
