package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

public class IRVVotingSystem extends VotingSystem {

    public IRVVotingSystem(Election election) {
        super(election);
    }

    @Override
    Vote loadVote(String vote) {
        //TODO: IMPLEMENT
        return null;
    }

    @Override
    Candidate determineWinner() {
        //TODO: IMPLEMENT
        return null;
    }
}
