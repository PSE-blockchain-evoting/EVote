package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.control.SupervisorViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol.SupervisorControl;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.statemanagement.SupervisorElection;
import edu.kit.iti.formal.pse2018.evote.view.components.SupervisorGUIPanel;

import javax.swing.JFrame;

/**
 * This class creates the Main GUI for the supervisor.
 */
public class SupervisorGUI extends JFrame {

    private SupervisorAdapter adapter;

    private SupervisorGUIPanel currentPanel;

    /**
     * Creates a new instance of SupervisorGUI
     */
    public SupervisorGUI() {
        ElectionStatusListener listener = new SupervisorElectionEndListenerImpl(this);

        SupervisorViewToModelIF model = new SupervisorElection(listener);
        SupervisorViewToControlIF control = new SupervisorControl(this, model);
        adapter = new SupervisorAdapter(control, model);

        currentPanel = new SupervisorAuthentication(adapter);
    }

    private void initComponents() {

    }



}
