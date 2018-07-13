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
            gui.showFrontpage();
        } catch (NetworkException e) {
            gui.showError(lang.getString("authFailed"));
        } catch (AuthenticationException e) {
            gui.showError(lang.getString("authFailed"));
        } catch (InternalSDKException e) {
            gui.showError(lang.getString("authFailed"));
        } catch (NetworkConfigException e) {
            gui.showError(lang.getString("authFailed"));
        }
    }
}
