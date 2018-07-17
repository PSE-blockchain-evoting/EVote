package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

public class VoterVSComponentManagerBuilder {

    private VoterVSComponentManagerBuilder() {

    }

    /**
     * Builds the correct VoterVSComponentManager based on the Voting System.
     * @param system The VotingSystem.
     * @param adapter The context where the ComponentManager is needed.
     * @return A VoterVSComponentManager instance.
     */
    public static VoterVSComponentManager generateComponentManager(
            VotingSystemType system, VoterAdapter adapter) {
        switch (system) {
            case ABSOLUTEMAJORITY:
            case RELATIVEMAJORITY:
                return new VoterMajorityVotingSystemComponentManager(adapter);
            case INSTANTRUNOFF:
                return new VoterIRVComponentManager(adapter);
            default:
                throw new IllegalArgumentException("Unknown Voting System type");
        }
    }
}
