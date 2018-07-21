package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.Entry;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.components.StackedBarChart;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.GapExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.TextExtension;

import java.awt.Color;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class SupervisorIRVComponentManager extends SupervisorVSComponentManager {

    private TextExtension canName;
    private TextExtension canVotes;

    /**
     * Creates an SupervisorIRVComponentManager instance.
     *
     * @param adapter The context of the UI.
     */
    public SupervisorIRVComponentManager(SupervisorAdapter adapter) {
        super(adapter);
        chart = new StackedBarChart();
        chart.setData(adapter.getResults());

        Font fbig = (Font) UIManager.get("Vote.font");

        Font v = new FontUIResource("Monospace", Font.BOLD, 30);

        ElectionDataIF data = adapter.getElectionData();
        String[] candidates = data.getCandidates();

        canVotes = new TextExtension(null, fbig, null);
        GapExtension ge = new GapExtension(canVotes, null);
        canName = new TextExtension(ge, fbig, candidates);
        table = new ExtendableList(canName);
        canName.setList(table);
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

        ElectionDataIF elData = adapter.getElectionData();
        String[] cand = elData.getCandidates();

        for (int i = 0; i < cand.length; i++) {
            canName.setText(i, cand[i]);
        }

        int size = 0;
        for (int i = 1; i < data.length - 1; i++) {
            int n = String.valueOf(data[i]).length();
            if (n > size) {
                size = n;
            }
        }
        size += 1;

        int pad = 5;
        for (int voteNr = 0; voteNr < cand.length; voteNr++) {
            String svote = "";

            for (int candIdx = 0; candIdx < cand.length; candIdx++) {
                int votes = getVotes(voteNr, candIdx, data);
                String s = "";
                for (int i = 0; i < size - String.valueOf(votes).length(); i++) {
                    s += "  ";
                }
                s += votes;
                svote += s;
            }
            canVotes.setText(voteNr, svote);
        }

        int i = 0;
        for (Entry e : table.getEntries()) {
            e.setBackground(i % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
            i++;
        }
    }

    private int getVotes(int voteNr, int cand, int[] data) {
        int col = data[0];
        int rows = (data.length - 1) / data[0];

        return data[1 + voteNr * col + cand];
    }
}
