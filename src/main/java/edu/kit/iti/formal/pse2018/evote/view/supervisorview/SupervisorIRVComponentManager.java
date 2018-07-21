package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.components.PieChart;

public class SupervisorIRVComponentManager extends SupervisorVSComponentManager {

    private Diagram chart;
    private ExtendableList table;

    /**
     * Creates an SupervisorIRVComponentManager instance.
     *
     * @param adapter The context of the UI.
     */
    public SupervisorIRVComponentManager(SupervisorAdapter adapter) {
        super(adapter);
        chart = new PieChart();
        chart.setData(adapter.getResults());
        table = new ExtendableList(null);
    }

    @Override
    public Diagram createResultDiagram() {
        return chart;
    }

    @Override
    public ExtendableList createResultTable() {
        return table;
    }

    @Override
    public void updateComponents(int[] data) {
        chart.setData(data);
    }
}
