package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

public interface ControlToModelIF {

    public boolean authenticate(String path);

    public ElectionDataIF getElectionData();

    public String getWinner();
}
