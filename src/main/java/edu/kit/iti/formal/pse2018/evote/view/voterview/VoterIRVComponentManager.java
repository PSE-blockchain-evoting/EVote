package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

public class VoterIRVComponentManager extends VoterVSComponentManager {

    public VoterIRVComponentManager(VoterAdapter adapter) {
        super(adapter);
    }

    @Override
    public Diagram createResultDiagram() {
        return null;
    }

    @Override
    public ExtendableList createVotingForm() {
        return null;
    }

    @Override
    public String getVote() {
        return null;
    }

    @Override
    public void setVote(String vote) {

    }

    @Override
    public void setEditable(boolean b) {

    }
}
