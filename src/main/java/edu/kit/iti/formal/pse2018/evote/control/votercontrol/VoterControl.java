package edu.kit.iti.formal.pse2018.evote.control.votercontrol;

import edu.kit.iti.formal.pse2018.evote.control.VoterViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.VoterControlToViewIF;

import java.awt.event.ActionListener;

public class VoterControl implements VoterViewToControlIF {

    private VotedListener votedListener;
    private VoterAuthenticationListener authListener;
    private VoterLogoutListener logoutListener;

    /**
     *
     * @param gui
     * @param model
     */
    public VoterControl(VoterControlToViewIF gui, VoterControlToModelIF model) {
        this.votedListener = new VotedListener(gui, model);
        this.authListener = new VoterAuthenticationListener(gui, model);
        this.logoutListener = new VoterLogoutListener(gui, model);
    }

    @Override
    public ActionListener getVotedListener() {
        return votedListener;
    }

    @Override
    public ActionListener getAuthenticationListener() {
        return authListener;
    }

    @Override
    public ActionListener getLogoutListener() {
        return logoutListener;
    }
}
