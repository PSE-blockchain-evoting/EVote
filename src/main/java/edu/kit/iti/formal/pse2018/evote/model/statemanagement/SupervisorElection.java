package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorSDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.SupervisorSDKInterfaceImpl;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssues;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssuesImpl;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.ResourceBundle;


public class SupervisorElection extends Election implements SupervisorControlToModelIF, SupervisorViewToModelIF {

    private SupervisorSDKInterface supervisorSDKInterface;

    private ConfigIssuesImpl configIssuesImpl = new ConfigIssuesImpl();

    public SupervisorElection(ElectionStatusListener electionStatusListener) {
        super(electionStatusListener);
    }

    /**
     * this method checks the validity of the configuration of an election.
     * @param electionDataIF the meta-data of an election contained in an {@code ElectionData} object
     * @return true if every election configuration entry complies with the standards, returns false else
     */
    private boolean checkElectionConfiguration(ElectionDataIF electionDataIF) {
        boolean value = true;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ConfigIssues.properties");

        if (!electionDataIF.getName().contains("\\w")) {
            configIssuesImpl.setNameIssue(resourceBundle.getString("name_issue"));
            value = false;
        }

        //checking if there are at least two candidates
        if (electionDataIF.getCandidates().length <= 1) {
            configIssuesImpl.setCandidateIssue(resourceBundle.getString("candidate_length_issue"));
            value = false;
        }

        //checking if any candidate name is empty
        if (!Arrays.stream(electionDataIF.getCandidates()).allMatch(x -> x.contains("\\w"))) {
            configIssuesImpl.setCandidateIssue(resourceBundle.getString("candidate_issue"));
            value = false;
        }

        //checking if there is at least one voter
        if (electionDataIF.getVoterCount() < 1) {
            configIssuesImpl.setVoterIssue(resourceBundle.getString("voter_length_issue"));
            value = false;
        }

        //check if starting time comes after ending time
        if (electionDataIF.getStartDate().after(electionDataIF.getEndDate())) {
            configIssuesImpl.setTimespanIssue(resourceBundle.getString("timespan_issue"));
            value = false;
        }
        return value;
    }

    /**
     * sets all meta-data from the election within the collections in the {@code AbsoluteMajorityVoting}-Class.
     * @param electionDataIF this contains the election meta-data
     * @return true, if the meta-data complies with election standards
     */
    public boolean setElectionData(ElectionDataIF electionDataIF) {
        if (!checkElectionConfiguration(electionDataIF)) {
            return false;
        }

        this.electionDataIF = electionDataIF;
        String [] candidatesTemp = electionDataIF.getCandidates();
        String [] descriptionsTemp = electionDataIF.getCandidateDescriptions();

        for (int i = 0; i < candidatesTemp.length - 1; i++) {
            candidateList[i] = (new Candidate(candidatesTemp[i], descriptionsTemp[i]));
        }
        return true;
    }


    @Override
    public void importConfig(String path) {

    }

    @Override
    public void exportConfig(String path) {
        File f = new File(path + File.separator + "ElectionConfiguration");

        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(f);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Election Name:" + electionDataIF.getName() + "\n");
            bufferedWriter.write("Election Description" + electionDataIF.getDescription() + "\n");
            bufferedWriter.write("");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setVoters(String[] names) {
        for (int i = 0; i < voterList.length - 1; i++) {

        }
    }

    @Override
    public boolean firstAuthentication(String username, String password) throws NetworkException,
            AuthenticationException, InternalSDKException, NetworkConfigException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        String filePath = resourceBundle.getString("electionSupervisor_Certificate");

        try {
            supervisorSDKInterface = SupervisorSDKInterfaceImpl
                    .createInstance(username, password, filePath, sdkEventListenerImpl);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public String[] getVotes() {
        return new String[0];
    }

    @Override
    public void startElection() throws NetworkException, NetworkConfigException {
        supervisorSDKInterface.createElection(electionDataIF);
    }

    @Override
    public void destroyElection() {
        //TODO
    }

    @Override
    public void authenticate(String path) throws NetworkException, AuthenticationException,
            InternalSDKException, NetworkConfigException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config.properties");
        String filePath = resourceBundle.getString("electionSupervisor_Certificate");

        supervisorSDKInterface = SupervisorSDKInterfaceImpl.createInstance(filePath, sdkEventListenerImpl);
    }

    @Override
    public ElectionDataIF getElectionData() {
        return electionDataIF;
    }

    @Override
    public String getWinner() {
        return null;
    }


    @Override
    public String[] getVoters() {
        return new String[0];
    }

    @Override
    public ConfigIssues getConfigIssues() {
        return null;
    }
}
