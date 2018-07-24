package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

import java.awt.Color;

public abstract class SupervisorVSComponentManager {

    protected static final Color[] CANDIDATE_COLORS = {Color.BLACK, Color.GREEN,
        Color.BLUE, Color.RED, Color.YELLOW, Color.PINK, Color.ORANGE};

    protected SupervisorAdapter adapter;

    protected Diagram chart;
    protected ExtendableList table;

    public SupervisorVSComponentManager(SupervisorAdapter adapter) {
        this.adapter = adapter;
    }

    public abstract Diagram createResultDiagram();

    public abstract ExtendableList createResultTable();

    public abstract void updateComponents(int[] data);
}
