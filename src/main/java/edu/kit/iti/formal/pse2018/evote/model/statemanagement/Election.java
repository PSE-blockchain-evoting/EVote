package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.util.LinkedList;
import java.util.List;

public abstract class Election {

    private List<Candidate> candidateList = new LinkedList<>();
    private List<Voter> voterList = new LinkedList<>();

    private ElectionDataIF electionDataIF;

    public void setElectionData(ElectionDataIF electionDataIF) {
        this.electionDataIF = electionDataIF;
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

    public void getResults() {
        //TODO: IMPLEMENT

    }

}
