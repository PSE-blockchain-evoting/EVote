package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class FinishElectionListener extends SupervisorEventListener {

    public FinishElectionListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorControl");
        boolean b = gui.electionOver();

        if (b) {
            gui.showResults();
        } else {
            gui.showError(lang.getString("finishElectionBad"));
        }

        String[] votes = model.getVotes();
        String winner = model.getWinner();
    }
}