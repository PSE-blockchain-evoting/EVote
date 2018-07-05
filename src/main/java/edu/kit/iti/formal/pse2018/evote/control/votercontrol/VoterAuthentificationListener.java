package edu.kit.iti.formal.pse2018.evote.control.votercontrol;

import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.VoterControlToViewIF;

import java.awt.event.ActionEvent;

public class VoterAuthentificationListener extends VoterEventListener {

    public VoterAuthentificationListener(VoterControlToViewIF gui, VoterControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String path = gui.getAuthenticationPath();
        boolean b = model.authenticate(path);

        if (b) {
            gui.showChoice();
        }
    }
}
