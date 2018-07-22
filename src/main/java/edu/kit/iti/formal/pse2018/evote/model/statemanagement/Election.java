package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.LoadVoteException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SDKInterface;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;


public abstract class Election {

    protected SDKInterface sdkInterfaceImpl;

    protected SDKEventListenerImpl sdkEventListenerImpl;

    protected ElectionDataIF electionDataIF;

    private VotingSystem votingSystem;

    /**
     * Creates an instance of Election. Election is the heart of the model.
     * It manages the related data and logic for Election.
     * @param electionStatusListener The GUI listener for election events.
     * @throws NetworkException Exception within the SDKConnection is thrown.
     * @throws NetworkConfigException The network was wrongly configured.
     */
    public Election(ElectionStatusListener electionStatusListener) throws NetworkException, NetworkConfigException {
        sdkEventListenerImpl = new SDKEventListenerImpl(this);
        sdkEventListenerImpl.setElectionStatusListener(electionStatusListener);
        sdkEventListenerImpl.start();

    }

    public void setElectionStatusListener(ElectionStatusListener listener) {
        sdkEventListenerImpl.setElectionStatusListener(listener);
    }

    public ElectionDataIF getElectionData() {
        return electionDataIF;
    }

    public void checkElectionOver() throws NetworkException, NetworkConfigException {
        sdkInterfaceImpl.dispatchElectionOverCheck();
    }

    public String getWinner() {
        return votingSystem.determineWinner();
    }

    public int[] getResults() {
        return votingSystem.determineResults();
    }

    protected void loadSDKData() throws NetworkException, NetworkConfigException,
            LoadVoteException, WrongCandidateNameException {
        assert (sdkInterfaceImpl != null);
        electionDataIF = sdkInterfaceImpl.getElectionData();
        reloadVotes();
    }

    protected void reloadVotes() throws NetworkException, NetworkConfigException,
            WrongCandidateNameException, LoadVoteException {
        votingSystem = VotingSystemBuilder.generateVotingSystem(electionDataIF.getVotingSystem(), this);
        String[] votes = sdkInterfaceImpl.getAllVotes();
        for (String vote : votes) {
            votingSystem.loadVote(vote);
        }
    }
}
