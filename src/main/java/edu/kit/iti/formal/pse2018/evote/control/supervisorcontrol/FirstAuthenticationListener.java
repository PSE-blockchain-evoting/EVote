package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class FirstAuthenticationListener extends SupervisorEventListener {

    public FirstAuthenticationListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorControl");
        String name = gui.getUsername();
        String password = gui.getPassword();
        boolean b = model.firstAuthentication(name, password);

        if (b) {
            gui.showFrontpage();
        } else {
            gui.showError(lang.getString("authFailed"));
        }
    }
}
