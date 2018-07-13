package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

public class VotingSystemBuilder {
    private final String instantRunoffVS = "InstantRunoffVoting";
    private final String absoluteMajorityVS = "AbsoluteMajorityVoting";
    private final String relativeMajorityVS = "RelativeMajorityVoting";

    private VotingSystemBuilder() {
    }

    /**
     * this method generates a voting system depending on the {@code VotingSystemType} that is passed as argument.
     * @param votingSystemType determines which voting system object is created
     * @param election the interfaces that contains the elections meta-data
     * @return
     */
    public VotingSystem generateVotingSystem(VotingSystemType votingSystemType, Election election) {
        switch (votingSystemType) {
            case INSTANTRUNOFF: return new IRVVotingSystem(election);
            case ABSOLUTEMAJORITY: return new AbsoluteMajorityVotingSystem(election);
            case RELATIVEMAJORITY: return new RelativeMajorityVotingSystem(election);
            default: throw new Error();
        }
        //TODO: THIS SEEMS BUGGY BUT THIS LINE SHOULD NEVER BE REACHED
        // AS WE CHECK THE INPUT PARAMETERS FOR VALIDITY FIRST
    }
}
