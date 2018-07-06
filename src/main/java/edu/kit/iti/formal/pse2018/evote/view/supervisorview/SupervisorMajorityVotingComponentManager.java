package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

public class SupervisorMajorityVotingComponentManager extends SupervisorVSComponentManager{

    public SupervisorMajorityVotingComponentManager(SupervisorAdapter adapter) {
        super(adapter);
    }

    @Override
    public Diagram createResultDiagram() {
        return null;
    }

    @Override
    public ExtendableList createResultTable() {
        return null;
    }
}
