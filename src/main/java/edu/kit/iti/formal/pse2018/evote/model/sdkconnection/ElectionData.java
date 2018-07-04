package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionEndCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

import java.util.Date;

public class ElectionData implements ElectionDataIF {

    private String name;
    private String description;
    private VotingSystemType votingSystem;
    private String[] candidates;
    private String[] candidateDescriptions;
    private Date startDate;
    private Date endDate;
    private ElectionEndCondition endCondition;
    private int voterCount;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public VotingSystemType getVotingSystem() {
        return this.votingSystem;
    }

    @Override
    public String[] getCandidates() {
        return this.candidates;
    }

    @Override
    public String[] getCandidateDescriptions() {
        return this.candidateDescriptions;
    }

    @Override
    public Date getStartDate() {
        return this.startDate;
    }

    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    @Override
    public ElectionEndCondition getEndCondition() {
        return this.endCondition;
    }

    @Override
    public int getVoterCount() {
        return this.voterCount;
    }
}
