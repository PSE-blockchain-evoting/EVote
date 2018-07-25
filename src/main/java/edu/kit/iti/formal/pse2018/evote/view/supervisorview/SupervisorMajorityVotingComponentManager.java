package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.Entry;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.components.PieChart;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.GapExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.NumberedExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.TextExtension;

import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;
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
        Font fbig = (Font) UIManager.get("Vote.font");

        canRes = new TextExtension(null, fbig, null);
        canRes.setColors(SupervisorVSComponentManager.CANDIDATE_COLORS);
        GapExtension ge = new GapExtension(canRes, null);
        canName = new TextExtension(ge, fbig, null);
        canName.setColors(SupervisorVSComponentManager.CANDIDATE_COLORS);
        NumberedExtension ne = new NumberedExtension(canName, fbig);
        table = new ExtendableList(ne);
        ne.setList(table);
        chart = new PieChart();
        chart.setColors(SupervisorVSComponentManager.CANDIDATE_COLORS);
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
    public void updateComponents(int[] results) {
        ElectionDataIF data = adapter.getElectionData();
        chart.setData(results);

        String[] candidates = data.getCandidates();
        ResourceBundle lang = ResourceBundle.getBundle("View");
        for (int i = 0; i < candidates.length; i++) {
            canName.setText(i, candidates[i]);
            canRes.setText(i, results[i] + " " + lang.getString("lblVoteText"));
        }

        int i = 0;
        for (Entry e : table.getEntries()) {
            e.setBackground(i % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
            i++;
        }
    }
}
