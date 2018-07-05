package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;

public class ConfirmedConfigListener extends SupervisorEventListener {

    public ConfirmedConfigListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        super(gui, model);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

    }
}
