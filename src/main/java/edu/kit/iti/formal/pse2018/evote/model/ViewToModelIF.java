package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

public interface ViewToModelIF {

    public ElectionDataIF getElectionData();

    public String getWinner();

    public void setElectionStatusListener(ElectionStatusListener listener);

    public int[] getResults();
}
