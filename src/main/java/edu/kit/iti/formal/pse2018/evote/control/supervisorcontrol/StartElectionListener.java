package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;

public class StartElectionListener extends SupervisorEventListener {

    public StartElectionListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        boolean b = model.startElection();

        if (b) {
            gui.showSuccess("Die Wahl wurde erfolgreich gestartet");
        } else {
            gui.showError("Die Wahl könnte nicht gestartet");
        }
    }
}
