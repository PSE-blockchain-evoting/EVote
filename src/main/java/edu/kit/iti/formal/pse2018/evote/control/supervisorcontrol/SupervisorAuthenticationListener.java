package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.ElectionRunningException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.LoadVoteException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class SupervisorAuthenticationListener extends SupervisorEventListener {


    public SupervisorAuthenticationListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorControl");
        String authPath = gui.getAuthenticationPath();
        try {
            model.authenticate(authPath);
            if (model.isElectionInitialized()) {
                gui.showResults();
            } else {
                gui.showFrontpage();
            }
        } catch (NetworkException | AuthenticationException | InternalSDKException | NetworkConfigException e) {
            gui.showError(lang.getString("authFailed"));
            e.printStackTrace();
        } catch (WrongCandidateNameException | LoadVoteException e) {
            gui.showError(lang.getString("couldntLoadInitialData"));
            e.printStackTrace();
        } catch (ElectionRunningException e) {
            System.err.println("ElectionRunningException was not caught");
            e.printStackTrace();
        }
    }
}
