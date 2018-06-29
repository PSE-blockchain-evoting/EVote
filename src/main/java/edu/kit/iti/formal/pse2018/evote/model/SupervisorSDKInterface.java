package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.util.List;

public interface SupervisorSDKInterface extends SDKInterface {

    public List<String> getAllVotes();

    public void destroyElection();

    public void createUser(String name, String filePath);

    public boolean createElection(ElectionDataIF electionData);
}
