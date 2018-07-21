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
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class VoterMajorityVotingSystemComponentManager extends VoterVSComponentManager {

    private RadioSelectionExtension selection;

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
        TextExtension te = new TextExtension(ge, fbig, data.getCandidates());
        GapExtension ge0 = new GapExtension(te, fbig);

        Icon selected = null;
        Icon unselected = null;

        try {
            File sfile = new File("src/main/resources/selected.png");
            File nsfile = new File("src/main/resources/notSelected.png");
            BufferedImage imgsfile = ImageIO.read(sfile);
            BufferedImage imgnsfile = ImageIO.read(nsfile);
            selected = new ImageIcon(ImageIO.read(sfile));
            unselected = new ImageIcon(ImageIO.read(nsfile));
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
        chart.setData(adapter.getResults());
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
}
