package edu.kit.iti.formal.pse2018.evote.view.voterview;

import java.lang.invoke.WrongMethodTypeException;

import javax.swing.JPanel;

public abstract class VoterGUIPanel extends JPanel {

    protected VoterAdapter adapter;

    public VoterGUIPanel(VoterAdapter adapter) {
        this.adapter = adapter;
    }

    public String getVote() {
        throw new WrongMethodTypeException();
    }

    public String getAuthenticationPath() {
        throw new WrongMethodTypeException();
    }

    public void showVote() {
        throw new WrongMethodTypeException();
    }
}
