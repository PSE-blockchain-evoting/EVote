package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class StartElectionListener extends SupervisorEventListener {

    public StartElectionListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorControl");
        boolean b = model.startElection();

        if (b) {
            gui.showSuccess(lang.getString("startElectionSuccess"));
        } else {
            gui.showError(lang.getString("startElectionBad"));
        }
    }
}
