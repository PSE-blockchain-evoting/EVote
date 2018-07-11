package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorSDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.SupervisorSDKInterfaceImpl;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssues;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.*;
import java.util.ResourceBundle;


public class SupervisorElection extends Election implements SupervisorControlToModelIF, SupervisorViewToModelIF {

    private SupervisorSDKInterface supervisorSDKInterface;

    private ConfigIssues configIssues;

    public SupervisorElection(ElectionStatusListener electionStatusListener){
        super(electionStatusListener);
    }

    private void checkElectionConfiguration(ElectionDataIF electionDataIF){

        if (!electionDataIF.getName().contains("\\w]"))

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
            bufferedWriter.write("Election Name:" + electionDataIF.getName()+ "\n");
            bufferedWriter.write("Election Description" + electionDataIF.getDescription()+ "\n");
            bufferedWriter.write("");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setVoters(String[] names) {
        for (String voterName : names)
            voterList.add(new Voter(voterName));
    }

    @Override
    public boolean setElectionData(ElectionDataIF electionDataIF) {
        this.electionDataIF = electionDataIF;
        String [] candidatesTemp = electionDataIF.getCandidates();
        String [] descriptionsTemp = electionDataIF.getCandidateDescriptions();

        for (int i = 0; i < candidatesTemp.length - 1; i++){
            candidateList.add(new Candidate(candidatesTemp[i], descriptionsTemp[i]));
        }
        return true;
    }

    @Override
    public boolean firstAuthentication(String username, String password) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config.properties");
        String filePath = resourceBundle.getString("electionSupervisor_Certificate");

        try {
            supervisorSDKInterface = SupervisorSDKInterfaceImpl.createInstance(username, password, filePath, sdkEventListenerImpl);
        }
        catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public String[] getVotes() {
        return new String[0];
    }

    @Override
    public void startElection() {

    }

    @Override
    public boolean authenticate(String path) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config.properties");
        String filePath = resourceBundle.getString("electionSupervisor_Certificate");

        try {
            supervisorSDKInterface = SupervisorSDKInterfaceImpl.createInstance(filePath, sdkEventListenerImpl);
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
        return false;
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
    public void setElectionEndListener(ElectionStatusListener listener) {

    }

    @Override
    public String getVotingSystem() {
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
