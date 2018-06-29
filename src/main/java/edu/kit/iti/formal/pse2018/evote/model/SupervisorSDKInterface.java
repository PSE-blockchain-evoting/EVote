package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.IOException;
import java.util.List;

public interface SupervisorSDKInterface extends SDKInterface {

    public String[] getAllVotes();

    public void destroyElection();

    public void createUser(String name, String filePath) throws IOException;

    public boolean createElection(ElectionDataIF electionData);
}
