package edu.kit.iti.formal.pse2018.evote.control.votercontrol;

import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.VoterControlToViewIF;

import java.awt.event.ActionEvent;

public class VotedListener extends VoterEventListener {

    public VotedListener(VoterControlToViewIF gui, VoterControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String vote = gui.getVote();
        boolean voted = model.vote(vote);

        if (voted) {
            gui.showSuccess("Stimme erfolgreich abgegeben!");
            gui.showWait();
        } else {
            gui.showError("Stimme ist NICHT abgegeben!");
        }
    }
}
