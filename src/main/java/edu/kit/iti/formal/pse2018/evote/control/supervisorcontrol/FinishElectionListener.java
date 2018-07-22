package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
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
        try {
            model.destroyElection();
        } catch (NetworkException | NetworkConfigException e) {
            gui.showError(lang.getString("electionDestructionFaile"));
            e.printStackTrace();
        }
    }
}
