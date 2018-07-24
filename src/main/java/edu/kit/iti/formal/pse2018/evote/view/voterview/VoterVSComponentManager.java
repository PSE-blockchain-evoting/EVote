package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.view.components.Diagram;
import edu.kit.iti.formal.pse2018.evote.view.components.ExtendableList;

import java.awt.Color;

public abstract class VoterVSComponentManager {

    protected static final Color[] CANDIDATE_COLORS = {Color.BLACK, Color.GREEN,
        Color.BLUE, Color.RED, Color.YELLOW, Color.PINK, Color.ORANGE};

    protected VoterAdapter adapter;

    protected Diagram chart;
    protected ExtendableList table;

    public VoterVSComponentManager(VoterAdapter adapter) {
        this.adapter = adapter;
    }

    public abstract Diagram createResultDiagram();

    public abstract ExtendableList createVotingForm();

    public abstract String getVote();

    public abstract void setVote(String vote);

    public abstract void setEditable(boolean b);

    public abstract void enableColors(boolean b);
}
