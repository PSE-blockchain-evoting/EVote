package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.control.SupervisorViewToControlIF;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssues;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.awt.event.ActionListener;

public class SupervisorAdapter {
    private SupervisorViewToControlIF control;
    private SupervisorViewToModelIF model;

    public SupervisorAdapter(SupervisorViewToControlIF control, SupervisorViewToModelIF model) {
        this.control = control;
        this.model = model;
    }

    public ActionListener getFinishElectionListener() {
        return control.getFinishElectionListener();
    }

    public ActionListener getImportConfigListener() {
        return control.getImportConfigListener();
    }

    public ActionListener getExportConfigListener() {
        return control.getExportConfigListener();
    }

    public ActionListener getFirstAuthenticationListener() {
        return control.getFirstAuthenticationListener();
    }

    public ActionListener getConfirmedConfigListener() {
        return control.getConfirmedConfigListener();
    }

    public ActionListener getNewConfigListener() {
        return control.getNewConfigListener();
    }

    public ActionListener getStartElectionListener() {
        return control.getStartElectionListener();
    }

    public ActionListener getAuthenticationListener() {
        return control.getAuthenticationListener();
    }

    public ActionListener getLogoutListener() {
        return control.getLogoutListener();
    }

    public String[] getVoters() {
        return model.getVoters();
    }

    public ConfigIssues getConfigIssues() {
        return model.getConfigIssues();
    }

    public ElectionDataIF getElectionData() {
        return model.getElectionData();
    }

    public String getWinner() {
        return model.getWinner();
    }

    public void setElectionEndListener(ElectionStatusListener listener) {
        model.setElectionStatusListener(listener);
    }

    public int[] getResults() {
        return model.getResults();
    }
}
