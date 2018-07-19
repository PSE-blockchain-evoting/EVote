package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.FailedDetermineWinnerException;
import edu.kit.iti.formal.pse2018.evote.exceptions.LoadVoteException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;

public abstract class VotingSystem {
    private Election election;

    public VotingSystem(Election election) {
        this.election = election;
    }

    public int[] determineResults() {
        //TODO: ACHIM FRAGEN WAS HIER PASSIEREN SOLL
        return null;
    }

    abstract Vote loadVote(String vote) throws LoadVoteException, WrongCandidateNameException;

    abstract Candidate determineWinner() throws FailedDetermineWinnerException;
}
