package edu.kit.iti.formal.pse2018.evote.control.votercontrol;

import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.VoterControlToViewIF;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class VotedListener extends VoterEventListener {

    public VotedListener(VoterControlToViewIF gui, VoterControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ResourceBundle lang = ResourceBundle.getBundle("VoterControl");
        String vote = gui.getVote();
        boolean voted = model.vote(vote);

        if (voted) {
            gui.showSuccess(lang.getString("votedSuccess"));
            gui.showWait();
        } else {
            gui.showError(lang.getString("votedBad"));
        }
    }
}
