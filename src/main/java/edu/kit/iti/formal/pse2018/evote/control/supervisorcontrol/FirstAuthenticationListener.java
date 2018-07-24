package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
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
        try {
            model.firstAuthentication(name, password);
            if (model.isElectionInitialized()) {
                gui.showResults();
            } else {
                gui.showFrontpage();
            }
        } catch (NetworkException | AuthenticationException
                | NetworkConfigException | InternalSDKException e) {
            gui.showError(lang.getString("authFailed"));
        }
    }
}
