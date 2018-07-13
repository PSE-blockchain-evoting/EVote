package edu.kit.iti.formal.pse2018.evote.control.votercontrol;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
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
        try {
            model.vote(vote);
            gui.showSuccess(lang.getString("votedSuccess"));
            gui.showWait();
        } catch (NetworkException e) {
            gui.showError(lang.getString("votedBad"));
        } catch (NetworkConfigException e) {
            gui.showError(lang.getString("votedBad"));
        }
    }
}
