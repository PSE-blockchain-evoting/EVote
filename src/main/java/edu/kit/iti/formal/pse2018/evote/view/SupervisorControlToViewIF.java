package edu.kit.iti.formal.pse2018.evote.view;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.awt.event.ActionListener;

public interface SupervisorControlToViewIF extends ControlToViewIF {

    public void showFrontpage();

    public void startConfigMenu();

    public void loadConfigData();

    public String[] getVoters();

    public ElectionDataIF getElectionData();

    public String getImportPath();

    public String getExportPath();

    public void showConfigIssues();

    public String getPassword();

    public String getUsername();

    public void updateResult();
}
