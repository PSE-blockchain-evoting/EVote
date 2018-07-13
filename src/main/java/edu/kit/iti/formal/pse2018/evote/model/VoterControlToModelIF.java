package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;

public interface VoterControlToModelIF extends ControlToModelIF {

    public boolean vote(String vote) throws NetworkException, NetworkConfigException;
}
