package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

public class SupervisorResult extends SupervisorGUIPanel {

    private Diagram chart;
    private ExtendableList table;

    /**
     * Creates an instance of SupervisorResult.
     * @param adapter The Adapter to the Control and Model interfaces.
     * @param manager The ComponentManager for creating the correct diagram and table.
     */
    public SupervisorResult(SupervisorAdapter adapter, SupervisorVSComponentManager manager) {
        super(adapter);
        chart = manager.createResultDiagram();
        table = manager.createResultTable();
    }

    @Override
    public void updateResults(int[] results, String winner){

    }
}
