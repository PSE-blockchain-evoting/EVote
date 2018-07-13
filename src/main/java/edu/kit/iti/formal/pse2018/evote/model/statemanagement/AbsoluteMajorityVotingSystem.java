package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

public class AbsoluteMajorityVotingSystem extends MajorityVotingSystem {

    public AbsoluteMajorityVotingSystem(Election election) {
        super(election);
    }

    @Override
    Vote loadVote(String vote) {
        return null;
    }

    @Override
    Candidate determineWinner() {
        return null;
    }
}
