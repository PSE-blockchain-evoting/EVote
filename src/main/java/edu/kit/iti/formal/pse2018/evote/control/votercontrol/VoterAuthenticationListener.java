package edu.kit.iti.formal.pse2018.evote.control.votercontrol;

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
        boolean b = model.authenticate(path);

        if (b) {
            gui.showChoice();
        } else {
            gui.showError(lang.getString("voterAuthenticationBad"));
        }
    }
}
