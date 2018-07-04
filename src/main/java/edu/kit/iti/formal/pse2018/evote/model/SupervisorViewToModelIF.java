package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssues;

public interface SupervisorViewToModelIF extends ViewToModelIF {

    public String[] getVoters();

    public ConfigIssues getConfigIssues();
}
