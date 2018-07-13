package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.control.SupervisorViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssues;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

public class SupervisorElection implements SupervisorViewToModelIF, SupervisorControlToModelIF {

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
    public boolean authenticate(String path) {
        return false;
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
    public int[] getResults() {
        return new int[0];
    }

    @Override
    public void importConfig(String path) {
        
    }

    @Override
    public void exportConfig(String path) {

    }

    @Override
    public void setVoters(String[] names) {

    }

    @Override
    public boolean setElectionData(ElectionDataIF electionData) {
        return false;
    }

    @Override
    public boolean firstAuthentication(String username, String password) {
        return false;
    }

    @Override
    public String[] getVotes() {
        return new String[0];
    }

    @Override
    public boolean startElection() {
        return false;
    }
}
