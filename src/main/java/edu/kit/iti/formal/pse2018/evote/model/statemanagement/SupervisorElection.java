package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.LoadVoteException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionData;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorSDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.SupervisorSDKInterfaceImpl;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssues;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssuesImpl;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;


public class SupervisorElection extends Election implements SupervisorControlToModelIF, SupervisorViewToModelIF {

    private SupervisorSDKInterface supervisorSDKInterface;
    private ConfigIssuesImpl configIssuesImpl;

    private Voter[] voters;

    public SupervisorElection(ElectionStatusListener electionStatusListener) throws NetworkException,
            NetworkConfigException {
        super(electionStatusListener);
    }

    /**
     * this method checks the validity of the configuration of an election.
     *
     * @param electionDataIF the meta-data of an election contained in an {@code ElectionData} object
     * @return true if every election configuration entry complies with the standards, returns false else
     */
    private boolean checkElectionConfiguration(ElectionDataIF electionDataIF) {
        boolean value = true;
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ConfigIssues");

        ConfigIssuesImpl cgi = new ConfigIssuesImpl();
        if (!electionDataIF.getName().matches(".*\\w.*")) {
            cgi.setNameIssue(resourceBundle.getString("name_issue"));
            value = false;
        }

        //checking if there are at least two candidates
        if (electionDataIF.getCandidates().length <= 1) {
            cgi.setCandidateIssue(resourceBundle.getString("candidate_length_issue"));
            value = false;
        }

        //checking if any candidate name is empty
        if (!Arrays.stream(electionDataIF.getCandidates()).allMatch(x -> x.matches(".*\\w.*"))) {
            cgi.setCandidateIssue(resourceBundle.getString("candidate_issue"));
            value = false;
        }

        //checking if there is at least one voter
        if (electionDataIF.getVoterCount() < 1) {
            cgi.setVoterIssue(resourceBundle.getString("voter_length_issue"));
            value = false;
        }

        //check if starting time comes after ending time
        if (electionDataIF.getStartDate().after(electionDataIF.getEndDate())) {
            cgi.setTimespanIssue(resourceBundle.getString("timespan_issue"));
            value = false;
        }

        if (!value) {
            configIssuesImpl = cgi;
        }

        return value;
    }

    /**
     * sets all meta-data from the election within the collections in the {@code AbsoluteMajorityVoting}-Class.
     *
     * @param electionDataIF this contains the election meta-data
     * @return true, if the meta-data complies with election standards
     */
    public boolean setElectionData(ElectionDataIF electionDataIF) {
        if (!checkElectionConfiguration(electionDataIF)) {
            return false;
        }

        this.electionDataIF = electionDataIF;
        return true;
    }


    @Override
    public void importConfig(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        String input = new String(encoded, StandardCharsets.UTF_8);

        JsonReader json = Json.createReader(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        JsonObject obj = json.readObject();
        String[] voters = obj.getJsonArray("voters").getValuesAs(
            jsonValue -> jsonValue.toString().replaceAll("\"", "")).toArray(new String[0]);
        ElectionDataIF data = ElectionData.fromJSon(obj);
        //One could verify the import but it would require interface return type change to boolean
        electionDataIF = data;

        assert (data.getVoterCount() == voters.length);
        this.voters = new Voter[data.getVoterCount()];
        for (int i = 0; i < data.getVoterCount(); i++) {
            this.voters[i] = new Voter(voters[i]);
        }
    }

    @Override
    public void exportConfig(String path) throws IOException {
        assert (electionDataIF != null);
        assert (voters != null);

        JsonObjectBuilder builder = ElectionData.toJson(electionDataIF);
        String[] svoters = new String[voters.length];
        for (int i = 0; i < voters.length; i++) {
            svoters[i] = voters[i].getName();
        }
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(Arrays.asList(svoters));
        builder.add("voters", arrayBuilder);
        String exportString = builder.build().toString();

        File f = new File(path + File.separator + "ElectionConfiguration");
        f.getParentFile().mkdirs();
        try {
            f.createNewFile();
            FileWriter fileWriter = new FileWriter(f);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(exportString);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new IOException("Couldn't write export file; " + e.getMessage());
        }

    }

    @Override
    public void setVoters(String[] names) {
        voters = new Voter[names.length];
        for (int i = 0; i < voters.length; i++) {
            voters[i] = new Voter(names[i]);
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
            sdkInterfaceImpl = supervisorSDKInterface;
            loadSDKData();
        } catch (IOException e) {
            return false;
        } catch (WrongCandidateNameException | LoadVoteException e) {
            e.printStackTrace();
            return false;
        }
        sdkEventListenerImpl.start();
        return true;
    }

    @Override
    public String[] getVotes() {
        throw new UnsupportedOperationException("not sure what this is supposed to do");
    }

    @Override
    public void startElection() throws NetworkException, NetworkConfigException {
        supervisorSDKInterface.createElection(electionDataIF);
    }

    @Override
    public void destroyElection() throws NetworkException, NetworkConfigException {
        supervisorSDKInterface.destroyElection();
    }

    @Override
    public void authenticate(String path) throws NetworkException, AuthenticationException,
            InternalSDKException, NetworkConfigException, WrongCandidateNameException, LoadVoteException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        String filePath = resourceBundle.getString("electionSupervisor_Certificate");

        supervisorSDKInterface = SupervisorSDKInterfaceImpl.createInstance(filePath, sdkEventListenerImpl);
        sdkInterfaceImpl = supervisorSDKInterface;
        loadSDKData();
        sdkEventListenerImpl.start();
    }

    @Override
    public ElectionDataIF getElectionData() {
        return electionDataIF;
    }

    @Override
    public String getWinner() {
        return super.getWinner();
    }


    @Override
    public String[] getVoters() {
        String[] voterNames = new String[voters.length];
        for (int i = 0; i < voterNames.length - 1; i++) {
            voterNames[i] = voters[i].getName();
        }
        return voterNames;
    }

    @Override
    public ConfigIssues getConfigIssues() {
        return configIssuesImpl;
    }

    @Override
    protected void loadSDKData() throws NetworkException, NetworkConfigException,
            WrongCandidateNameException, LoadVoteException {
        super.loadSDKData();
    }
}
