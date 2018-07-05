package edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol;

import edu.kit.iti.formal.pse2018.evote.control.SupervisorViewToControlIF;

import java.awt.event.ActionListener;

public class SupervisorControl implements SupervisorViewToControlIF {

    private SupervisorEventListener listener;

    @Override
    public ActionListener getFinishElectionListener() {
        return null;
    }

    @Override
    public ActionListener getImportConfigListener() {
        return null;
    }

    @Override
    public ActionListener getExportConfigListener() {
        return null;
    }

    @Override
    public ActionListener getFirstAuthenticationListener() {
        return null;
    }

    @Override
    public ActionListener getConfirmedConfigListener() {
        return null;
    }

    @Override
    public ActionListener getNewConfigListener() {
        return null;
    }

    @Override
    public ActionListener getStartElectionListener() {
        return null;
    }

    @Override
    public ActionListener getAuthenticationListener() {
        return null;
    }

    @Override
    public ActionListener getLogoutListener() {
        return null;
    }
}
