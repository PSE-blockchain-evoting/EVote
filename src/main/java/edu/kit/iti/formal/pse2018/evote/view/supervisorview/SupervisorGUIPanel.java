package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import java.lang.invoke.WrongMethodTypeException;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class SupervisorGUIPanel extends JPanel {

    protected SupervisorAdapter adapter;
    private JLabel lblTitle;
    private JPanel pnlLogo;

    /**
     * Creates an instance of SupervisorGUIPanel.
     *
     * @param adapter The Adapter to the control and model interfaces.
     */
    public SupervisorGUIPanel(SupervisorAdapter adapter) {
        this.adapter = adapter;
    }

    public void updateResults(int[] results, String winner) {
        throw new WrongMethodTypeException();
    }

    public String getImportPath() {
        throw new WrongMethodTypeException();
    }

    public String getCertPath() {
        throw new WrongMethodTypeException();
    }

    public String getUsername() {
        throw new WrongMethodTypeException();
    }

    public String getPassword() {
        throw new WrongMethodTypeException();
    }
}
