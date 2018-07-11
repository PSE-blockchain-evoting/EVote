package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

public class VotingSystemBuilder {
    private final String instantRunoffVS = "InstantRunoffVoting";
    private final String absoluteMajorityVS = "AbsoluteMajorityVoting";
    private final String relativeMajorityVS = "RelativeMajorityVoting";

    private VotingSystemBuilder() {
    }

    public VotingSystem generateVotingSystem(VotingSystemType votingSystemType, Election election){
        switch (votingSystemType) {
            case INSTANTRUNOFF: return new IRVVotingSystem(election);
            case ABSOLUTEMAJORITY: return new AbsoluteMajorityVotingSystem(election);
            case RELATIVEMAJORITY: return new RelativeMajorityVotingSystem(election);
        }
        return null; //TODO: THIS SEEMS BUGGY BUT THIS LINE SHOULD NEVER BE REACHED AS WE CHECK THE INPUT PARAMETERS FOR VALIDITY FIRST
    }
}
