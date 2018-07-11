package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.util.LinkedList;
import java.util.List;

public abstract class Election {

    protected List<Candidate> candidateList = new LinkedList<>();
    protected List<Voter> voterList = new LinkedList<>();

    protected SDKEventListenerImpl sdkEventListenerImpl;

    protected ElectionDataIF electionDataIF;

    public Election(ElectionStatusListener electionStatusListener) {
        sdkEventListenerImpl = new SDKEventListenerImpl(this);
        sdkEventListenerImpl.setElectionStatusListener(electionStatusListener);
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

    public String[] getVotes() {
        //TODO: IMPLEMENT
        return null;
    }


    public int[] getResults() {
        //TODO: IMPLEMENT
        return null;
    }

}
