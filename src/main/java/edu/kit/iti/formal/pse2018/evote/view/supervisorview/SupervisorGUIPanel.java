package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class SupervisorGUIPanel extends JPanel {

    protected SupervisorAdapter adapter;
    private JLabel lblTitle;
    private JPanel pnlLogo;

    /**
     * Creates an instance of SupervisorGUIPanel.
     * @param adapter The Adapter to the control and model interfaces.
     */
    public SupervisorGUIPanel(SupervisorAdapter adapter) {
        this.adapter = adapter;

    }

}
