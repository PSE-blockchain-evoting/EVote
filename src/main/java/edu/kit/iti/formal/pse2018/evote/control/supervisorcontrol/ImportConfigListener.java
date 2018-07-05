package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;

public class ImportConfigListener extends SupervisorEventListener {



    public ImportConfigListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model)  {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String path = gui.getImportPath();
        model.importConfig(path);
        gui.loadConfigData();
    }
}
