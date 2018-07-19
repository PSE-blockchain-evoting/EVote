package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SDKInterface;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;


public abstract class Election {

    protected Candidate[] candidateList;
    protected Voter[] voterList;
    protected String[] votes;

    private SDKInterface sdkInterfaceImpl;

    protected SDKEventListenerImpl sdkEventListenerImpl;

    protected ElectionDataIF electionDataIF; //TODO: THIS REFERENCE IS NOT SET ANYWHERE YET

    private VotingSystem votingSystem;

    public Election(ElectionStatusListener electionStatusListener) {
        sdkEventListenerImpl = new SDKEventListenerImpl(this);
        sdkEventListenerImpl.setElectionStatusListener(electionStatusListener);
    }

    public void setElectionStatusListener(ElectionStatusListener listener) {
        sdkEventListenerImpl.setElectionStatusListener(listener);
    }

    public ElectionDataIF getElectionData() {
        return electionDataIF;
    }

    public void checkElectionOver() {
        //TODO: IMPLEMENT
    }

    public String getWinner() {
        //TODO: IMPLEMENT
        return null;
    }

    /**
     * returns an array that contains every vote as String.
     * @return the array containing every vote
     * @throws NetworkException interior network error
     * @throws NetworkConfigException interior network config error
     */
    public String[] getVotes() throws NetworkException, NetworkConfigException {
        if (votes == null) {
            votes = sdkInterfaceImpl.getAllVotes();
        }
        return votes;
    }

    public int[] getResults() throws NetworkException, NetworkConfigException {
        return votingSystem.determineResults();
    }


}
