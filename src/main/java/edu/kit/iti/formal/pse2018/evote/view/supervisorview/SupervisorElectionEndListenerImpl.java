package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;

public class SupervisorElectionEndListenerImpl implements ElectionStatusListener {

    private SupervisorGUI gui;

    public SupervisorElectionEndListenerImpl(SupervisorGUI supervisorGUI) {
        gui = supervisorGUI;
    }

    @Override
    public void electionOver() {
        gui.electionOver();
    }

    @Override
    public void electionUpdate() {
        gui.updateResult();
    }
}
