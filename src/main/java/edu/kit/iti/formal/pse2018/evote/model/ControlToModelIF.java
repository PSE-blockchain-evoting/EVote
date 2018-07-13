package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

public interface ControlToModelIF {

    public void authenticate(String path) throws NetworkException, AuthenticationException,
            InternalSDKException, NetworkConfigException;

    public ElectionDataIF getElectionData();

    public String getWinner();
}
