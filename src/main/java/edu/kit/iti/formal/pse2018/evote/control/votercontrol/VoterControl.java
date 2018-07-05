package edu.kit.iti.formal.pse2018.evote.control.votercontrol;

import edu.kit.iti.formal.pse2018.evote.control.VoterViewToControlIF;

import java.awt.event.ActionListener;

public class VoterControl implements VoterViewToControlIF {

    private VoterEventListener listener;

    @Override
    public ActionListener getVotedListener() {
        return null;
    }

    @Override
    public ActionListener getAuthenticationListener() {
        return null;
    }

    @Override
    public ActionListener getLogoutListener() {
        return null;
    }

    public void logout() {}

    public void voted() {}

    public void setState() {}
}
