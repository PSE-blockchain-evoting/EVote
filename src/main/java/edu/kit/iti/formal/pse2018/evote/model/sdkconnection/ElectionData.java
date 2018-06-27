package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

import java.util.Date;
import java.util.List;

public class ElectionData implements ElectionDataIF {

    private String name;
    private String description;
    private VotingSystemType votingSystem;
    private int candidates;
    private List<String> candidateDescriptions;
    private Date startDate;
    private Date endDate;
    //TODO: EndCondition
    private int voterCount;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public VotingSystemType getVotingSystem() {
        return null;
    }

    @Override
    public int getCandidates() {
        return 0;
    }

    @Override
    public List<String> getCandidateDescriptions() {
        return null;
    }

    @Override
    public Date getStartDate() {
        return null;
    }

    @Override
    public Date getEndDate() {
        return null;
    }

    @Override
    public void getEndCondition() {

    }

    @Override
    public int getVoterCount() {
        return 0;
    }
}
