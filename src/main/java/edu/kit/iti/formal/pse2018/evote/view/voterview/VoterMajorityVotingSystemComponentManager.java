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

package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.Entry;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.components.PieChart;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.DescriptionExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.GapExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.RadioSelectionExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.TextExtension;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class VoterMajorityVotingSystemComponentManager extends VoterVSComponentManager {

    private RadioSelectionExtension selection;
    private TextExtension names;

    /**
     * Create an instance of VoterMajorityVotingSystemComponentManager.
     *
     * @param adapter The context of the ComponentManager.
     */
    public VoterMajorityVotingSystemComponentManager(VoterAdapter adapter) {
        super(adapter);
        Font f = (Font) UIManager.get("General.font");
        Font fbig = (Font) UIManager.get("Vote.font");

        ElectionDataIF data = adapter.getElectionData();
        DescriptionExtension de = new DescriptionExtension(null, fbig, null);
        de.setEditable(false);
        de.setButtonText("!");
        GapExtension ge = new GapExtension(de, fbig);
        names = new TextExtension(ge, fbig, data.getCandidates());
        GapExtension ge0 = new GapExtension(names, fbig);

        Icon selected = null;
        Icon unselected = null;

        try {
            selected = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/selected.png")));
            unselected = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/notSelected.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        selection = new RadioSelectionExtension(ge0, fbig, selected, unselected);
        table = new ExtendableList(selection);
        table.setBorder(null);
        selection.setList(table);

        for (int i = 0; i < data.getCandidates().length; i++) {
            de.setDescription(i, data.getCandidateDescriptions()[i]);
        }

        int i = 0;
        for (Entry e : table.getEntries()) {
            e.setBackground(i % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
            i++;
        }

        chart = new PieChart();
        chart.setColors(VoterVSComponentManager.CANDIDATE_COLORS);
        chart.setData(adapter.getResults());

        ResourceBundle lang = ResourceBundle.getBundle("View");
        lblTableDescription = new JLabel(lang.getString("majorityVSTableDescription"));
        lblTableDescription.setFont((Font)UIManager.get("Small.font"));
    }

    @Override
    public Diagram createResultDiagram() {
        return chart;
    }

    @Override
    public ExtendableList createVotingForm() {
        return table;
    }

    @Override
    public JLabel createTableDescriptionLabel() {
        return lblTableDescription;
    }

    @Override
    public String getVote() {
        String cand = null;
        ElectionDataIF data = adapter.getElectionData();

        int index = selection.getSelection();
        if (index >= 0) {
            cand = data.getCandidates()[index];
        }

        if (cand == null) {
            throw new IllegalStateException("No selection has be made");
        }

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("candidate", cand);
        return objectBuilder.build().toString();
    }

    @Override
    public void setVote(String vote) {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(vote.getBytes(StandardCharsets.UTF_8)));
        String cand = reader.readObject().getString("candidate");

        ElectionDataIF data = adapter.getElectionData();
        for (int i = 0; i < data.getCandidates().length; i++) {
            if (cand.equals(data.getCandidates()[i])) {
                selection.setSelection(i);
                break;
            }
        }
    }

    @Override
    public void setEditable(boolean b) {
        selection.setEnabled(b);
    }

    @Override
    public void enableColors(boolean b) {
        if (b) {
            names.setColors(VoterVSComponentManager.CANDIDATE_COLORS);
        } else {
            Color[] c = {Color.BLACK};
            names.setColors(c);
        }
    }
}
