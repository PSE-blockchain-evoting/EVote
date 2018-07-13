package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;

public interface VoterSDKInterface extends SDKInterface {

    public void vote(String vote) throws NetworkException, NetworkConfigException;

    public String getOwnVote() throws NetworkException, NetworkConfigException;
}
