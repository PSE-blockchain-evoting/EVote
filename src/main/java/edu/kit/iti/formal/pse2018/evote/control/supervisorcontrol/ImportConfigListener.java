package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ResourceBundle;

public class ImportConfigListener extends SupervisorEventListener {



    public ImportConfigListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model)  {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorControl");
        String path = gui.getImportPath();
        try {
            model.importConfig(path);
        } catch (IOException e) {
            gui.showError(lang.getString("importFailed"));
            e.printStackTrace();
        }
        gui.startConfigMenu();
        gui.loadConfigData();
    }
}
