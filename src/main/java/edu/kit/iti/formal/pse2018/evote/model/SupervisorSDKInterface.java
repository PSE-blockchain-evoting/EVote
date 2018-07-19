package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.exceptions.EnrollmentException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.IOException;
import java.util.List;

public interface SupervisorSDKInterface extends SDKInterface {

    public void destroyElection() throws NetworkException, NetworkConfigException;

    public void createUser(String name, String filePath) throws IOException, EnrollmentException;

    public void createElection(ElectionDataIF electionData) throws NetworkException, NetworkConfigException;
}
