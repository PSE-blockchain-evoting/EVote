package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;

public class VoterElectionEndListenerImpl implements ElectionStatusListener {
    private VoterGUI gui;

    public VoterElectionEndListenerImpl(VoterGUI voterGUI) {
        this.gui = voterGUI;
    }

    @Override
    public void electionOver() {
        gui.electionOver();
    }

    @Override
    public void electionUpdate() {

    }
}
