/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
import java.util.ResourceBundle;
import javax.swing.JLabel;
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

        ElectionDataIF data = adapter.getElectionData();
        String[] candidates = data.getCandidates();

        ResourceBundle lang = ResourceBundle.getBundle("SupervisorView");
        String[] labels = new String[candidates.length];
        for (int i = 0; i < labels.length; i++) {
            labels[i] = (i + 1) + ". " + lang.getString("vote");
        }

        StackedBarChart sbc = new StackedBarChart();
        sbc.setLabels(labels);
        chart = sbc;
        chart.setColors(SupervisorVSComponentManager.CANDIDATE_COLORS);
        chart.setData(adapter.getResults());

        Font fbig = (Font) UIManager.get("Vote.font");

        Font v = new FontUIResource("Monospace", Font.BOLD, 30);


        canVotes = new TextExtension(null, fbig, null);
        canVotes.setColors(SupervisorVSComponentManager.CANDIDATE_COLORS);
        GapExtension ge = new GapExtension(canVotes, null);
        canName = new TextExtension(ge, fbig, candidates);
        canName.setColors(SupervisorVSComponentManager.CANDIDATE_COLORS);
        table = new ExtendableList(canName);
        canName.setList(table);

        ResourceBundle l = ResourceBundle.getBundle("View");
        lblTableDescription = new JLabel(l.getString("IRVVSTableDescription"));
        lblTableDescription.setFont((Font) UIManager.get("Small.font"));
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
    public JLabel createTableDescriptionLabel() {
        return lblTableDescription;
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

        return data[1 + cand * col + voteNr];
    }
}
