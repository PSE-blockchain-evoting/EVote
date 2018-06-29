package edu.kit.iti.formal.pse2018.evote.control;

import java.awt.event.ActionListener;

public interface SupervisorViewToControlIF extends ViewToControlIF {

    public ActionListener getFinishElectionListener();

    public ActionListener getImportConfigListener();

    public ActionListener getExportConfigListener();

    public ActionListener getFirstAuthenticationListener();

    public ActionListener getConfirmedConfigListener();

    public ActionListener getNewConfigListener();

    public ActionListener getStartElectionListener();
}
