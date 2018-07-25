package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.Entry;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;
import edu.kit.iti.formal.pse2018.evote.view.components.StackedBarChart;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.DescriptionExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.GapExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.RankingExtension;
import edu.kit.iti.formal.pse2018.evote.view.components.listextensions.TextExtension;

import java.awt.Color;
import java.awt.Font;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonReader;
import javax.json.JsonString;
import javax.swing.UIManager;

public class VoterIRVComponentManager extends VoterVSComponentManager {

    private TextExtension names;
    private RankingExtension re;

    /**
     * Creates an instance of VoterIRVComponentManager.
     *
     * @param adapter The context of the GUI.
     */
    public VoterIRVComponentManager(VoterAdapter adapter) {
        super(adapter);

        ElectionDataIF data = adapter.getElectionData();
        Font f = (Font) UIManager.get("Vote.font");
        DescriptionExtension de = new DescriptionExtension(null, f, null);
        de.setEditable(false);
        de.setButtonText("!");
        GapExtension ge0 = new GapExtension(de, f);
        TextExtension names = new TextExtension(ge0, f, data.getCandidates());
        GapExtension ge1 = new GapExtension(names, f);
        re = new RankingExtension(ge1, f, data.getCandidates().length);

        table = new ExtendableList(re);
        table.setBorder(null);
        re.setList(table);

        for (int i = 0; i < data.getCandidates().length; i++) {
            de.setDescription(i, data.getCandidateDescriptions()[i]);
        }

        int i = 0;
        for (Entry e : table.getEntries()) {
            e.setBackground(i % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
            i++;
        }

        chart = new StackedBarChart();
        chart.setColors(VoterVSComponentManager.CANDIDATE_COLORS);
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
        int[] ranking = re.getRanking();
        int size = 0;
        for (int i = 0; i < ranking.length; i++) {
            if (ranking[i] != 0) {
                size++;
            }
        }

        String[] vote = new String[size];

        ElectionDataIF data = adapter.getElectionData();
        String[] candidates = data.getCandidates();
        for (int i = 1; i <= size; i++) {
            for (int j = 0; j < candidates.length; j++) {
                if (ranking[j] == i) {
                    vote[i - 1] = candidates[j];
                }
            }
        }

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(Arrays.asList(vote));
        String res = arrayBuilder.build().toString();
        System.out.println("VoteString = " + res);
        return res;
    }

    @Override
    public void setVote(String vote) {
        System.out.println("setVote Vote = " + vote);
        ElectionDataIF data = adapter.getElectionData();
        JsonReader reader = Json.createReader(new ByteArrayInputStream(vote.getBytes(StandardCharsets.UTF_8)));

        List<JsonString> test = reader.readArray().getValuesAs(JsonString.class);

        String[] candiates = data.getCandidates();
        int[] ranking = new int[candiates.length];
        for (int i = 0; i < test.size(); i++) {
            String cand = test.get(i).getString();

            for (int j = 0; j < candiates.length; j++) {
                if (cand.equals(candiates[j])) {
                    ranking[j] = i + 1;
                    break;
                }
            }
        }

        System.out.println("ranking = " + Arrays.toString(ranking));
        re.setRanking(ranking);
    }

    @Override
    public void setEditable(boolean b) {
        re.setEditable(b);
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
