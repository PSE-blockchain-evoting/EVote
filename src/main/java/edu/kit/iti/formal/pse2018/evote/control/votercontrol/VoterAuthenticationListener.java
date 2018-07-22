package edu.kit.iti.formal.pse2018.evote.control.votercontrol;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.LoadVoteException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.VoterControlToViewIF;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class VoterAuthenticationListener extends VoterEventListener {

    public VoterAuthenticationListener(VoterControlToViewIF gui, VoterControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ResourceBundle lang = ResourceBundle.getBundle("VoterControl");
        String path = gui.getAuthenticationPath();
        try {
            model.authenticate(path);
            gui.showChoice();
        } catch (InternalSDKException | NetworkException | AuthenticationException | NetworkConfigException e) {
            gui.showError(lang.getString("voterAuthenticationBad"));
            e.printStackTrace();
        } catch (WrongCandidateNameException | LoadVoteException e) {
            gui.showError(lang.getString("couldntLoadInitialData"));
            e.printStackTrace();
        }
    }
}
