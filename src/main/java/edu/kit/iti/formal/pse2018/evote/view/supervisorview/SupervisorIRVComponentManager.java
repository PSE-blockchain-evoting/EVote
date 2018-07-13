package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

public class SupervisorIRVComponentManager extends SupervisorVSComponentManager {

    public SupervisorIRVComponentManager(SupervisorAdapter adapter) {
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

    @Override
    public void updateComponents(int[] data) {
        //TODO:
        throw new UnsupportedOperationException();
    }
}
