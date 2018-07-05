package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

public interface SupervisorControlToModelIF extends ControlToModelIF {

    public void importConfig(String path);

    public void exportConfig(String path);

    public void setVoters(String[] names);

    public boolean setElectionData(ElectionDataIF electionData);

    public boolean firstAuthentication(String username, String password);

    public String[] getVotes();

    public boolean startElection();
}
