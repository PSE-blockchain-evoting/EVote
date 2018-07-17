package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.control.VoterViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.VoterViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.awt.event.ActionListener;

public class VoterAdapter {

    private VoterViewToModelIF model;
    private VoterViewToControlIF control;

    public VoterAdapter(VoterViewToControlIF control, VoterViewToModelIF model){
        this.model = model;
        this.control = control;
    }

    public ActionListener getVotedListener() {
        return control.getVotedListener();
    }

    public ActionListener getAuthenticationListener() {
        return control.getAuthenticationListener();
    }

    public ActionListener getLogoutListener() {
        return control.getLogoutListener();
    }

    public String getOwnVote() {
        return model.getOwnVote();
    }

    public ElectionDataIF getElectionData() {
        return model.getElectionData();
    }

    public String getWinner() {
        return model.getWinner();
    }

    public void setElectionStatusListener(ElectionStatusListener listener) {
        model.setElectionStatusListener(listener);
    }

    public int[] getResults() {
        return model.getResults();
    }
}
