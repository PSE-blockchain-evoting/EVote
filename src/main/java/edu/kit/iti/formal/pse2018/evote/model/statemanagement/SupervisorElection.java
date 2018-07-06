package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssues;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

public class SupervisorElection implements SupervisorViewToModelIF {
    public SupervisorElection(ElectionStatusListener listener) {
    }

    @Override
    public String[] getVoters() {
        return new String[0];
    }

    @Override
    public ConfigIssues getConfigIssues() {
        return null;
    }

    @Override
    public ElectionDataIF getElectionData() {
        return null;
    }

    @Override
    public String getWinner() {
        return null;
    }

    @Override
    public void setElectionEndListener(ElectionStatusListener listener) {

    }

    @Override
    public String getVotingSystem() {
        return null;
    }

    @Override
    public int[] getResults() {
        return new int[0];
    }
}
