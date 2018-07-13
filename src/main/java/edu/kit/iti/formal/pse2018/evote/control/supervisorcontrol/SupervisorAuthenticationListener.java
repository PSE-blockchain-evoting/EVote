package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
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
        try {
            model.authenticate(authPath);
            gui.showFrontpage();
        } catch (NetworkException e) {
            gui.showError("authFailed");
        } catch (AuthenticationException e) {
            gui.showError("authFailed");
        } catch (InternalSDKException e) {
            gui.showError("authFailed");
        } catch (NetworkConfigException e) {
            gui.showError("authFailed");
        }
    }
}
