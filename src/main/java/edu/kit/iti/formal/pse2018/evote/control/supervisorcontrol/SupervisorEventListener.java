package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionListener;

public abstract class SupervisorEventListener implements ActionListener {

    protected SupervisorControlToViewIF gui;
    protected SupervisorControlToModelIF model;

    public SupervisorEventListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        this.gui = gui;
        this.model = model;
    }
}
