package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

public abstract class SupervisorVSComponentManagerBuilder {

    private SupervisorVSComponentManagerBuilder() {

    }

    /**
     * Builds the correct SupervisorVSComponentManager based on the Voting System.
     * @param system The VotingSystem.
     * @param adapter The context where the ComponentManager is needed.
     * @return A SupervisorVSComponentManager instance.
     */
    public static SupervisorVSComponentManager generateComponentManager(
            VotingSystemType system, SupervisorAdapter adapter) {
        switch (system) {
            case ABSOLUTEMAJORITY:
            case RELATIVEMAJORITY:
                return new SupervisorMajorityVotingComponentManager(adapter);
            case INSTANTRUNOFF:
                return new SupervisorIRVComponentManager(adapter);
            default:
                throw new IllegalArgumentException("Unknown Voting System type");
        }
    }

}
