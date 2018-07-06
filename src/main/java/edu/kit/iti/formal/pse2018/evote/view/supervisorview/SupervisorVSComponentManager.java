package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

public abstract class SupervisorVSComponentManager {

    protected SupervisorAdapter adapter;

    protected Diagram chart;
    protected ExtendableList table;

    public SupervisorVSComponentManager(SupervisorAdapter adapter) {
        this.adapter = adapter;
    }

    public abstract Diagram createResultDiagram();

    public abstract ExtendableList createResultTable();
}
