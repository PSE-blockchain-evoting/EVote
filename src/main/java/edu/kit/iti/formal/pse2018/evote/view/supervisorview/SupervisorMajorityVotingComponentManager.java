package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.components.PieChart;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.NumberedExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.TextExtension;
import javax.xml.soap.Text;

import java.awt.Font;
import javax.swing.UIManager;

public class SupervisorMajorityVotingComponentManager extends SupervisorVSComponentManager {

    private TextExtension canName;
    private TextExtension canRes;

    /**
     * Creates an instance of SupervisorMajorityVotingComponentManager.
     *
     * @param adapter The context of the ComponentManager.
     */
    public SupervisorMajorityVotingComponentManager(SupervisorAdapter adapter) {
        super(adapter);
        Font f = (Font) UIManager.get("General.font");

        canRes = new TextExtension(null, f, null);
        canName = new TextExtension(canRes, f, null);
        NumberedExtension ne = new NumberedExtension(canName, f);
        table = new ExtendableList(ne);
        chart = new PieChart();
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
    public ExtendableList updateComponents(int[] results) {
        ElectionDataIF data = adapter.getElectionData();
        chart.setData(results);

        String[] candidates = data.getCandidates();
        for (int i = 0; i < candidates.length; i++) {
            canName.setText(i,candidates[i]);
            canRes.setText(i,results[i] + "");
        }
    }
}
