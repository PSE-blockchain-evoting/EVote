package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ResourceBundle;

public class ExportConfigListener extends SupervisorEventListener {

    public ExportConfigListener(SupervisorControlToViewIF gui, SupervisorControlToModelIF model) {
        super(gui, model);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        ResourceBundle lang = ResourceBundle.getBundle("SupervisorControl");
        String path = gui.getExportPath();
        try {
            model.exportConfig(path);
        } catch (IOException e) {
            gui.showError(lang.getString("exportFailed"));
            e.printStackTrace();
        } finally {
            gui.showSuccess(lang.getString("exportSuccess"));
        }
    }
}
