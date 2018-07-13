package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;

public class SupervisorAuthenticationListener extends SupervisorEventListener {


    public SupervisorAuthenticationListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String authPath = gui.getAuthenticationPath();
        boolean b = model.authenticate(authPath);
        if (b) {
            gui.showFrontpage();
        } else {
            gui.showError("authFailed");
        }

    }
}
