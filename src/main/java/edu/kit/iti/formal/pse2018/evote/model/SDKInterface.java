package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

public interface SDKInterface {

    public void dispatchElectionOverCheck();

    public ElectionDataIF getElectionData();
}
