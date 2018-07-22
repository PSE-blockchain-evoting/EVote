package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

public interface SDKInterface {

    public String[] getAllVotes() throws NetworkException, NetworkConfigException;

    public void dispatchElectionOverCheck() throws NetworkException, NetworkConfigException;

    public ElectionDataIF getElectionData()throws NetworkException, NetworkConfigException;

    public boolean isElectionInitialized() throws NetworkException, NetworkConfigException;
}
