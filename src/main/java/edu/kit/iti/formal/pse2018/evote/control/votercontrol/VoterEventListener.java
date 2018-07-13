package edu.kit.iti.formal.pse2018.evote.control.votercontrol;

import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.VoterControlToViewIF;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class VoterEventListener implements ActionListener {

    protected VoterControlToViewIF gui;
    protected VoterControlToModelIF model;

    public VoterEventListener(VoterControlToViewIF gui, VoterControlToModelIF model) {
        this.gui = gui;
        this.model = model;
    }
}
