package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

public class ConfirmedConfigListener extends SupervisorEventListener {

    public ConfirmedConfigListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        super(gui, model);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorControl");
        ElectionDataIF data = gui.getElectionData();
        model.setVoters(gui.getVoters());
        boolean b = model.setElectionData(data);
        if (b) {
            gui.showSuccess(lang.getString("confirmedConfigSuccess"));
        } else {
            gui.showConfigIssues();
            gui.showError(lang.getString("confirmedConfigBad"));
        }
    }
}
