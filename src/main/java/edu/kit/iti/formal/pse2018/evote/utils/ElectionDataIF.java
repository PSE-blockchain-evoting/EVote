package edu.kit.iti.formal.pse2018.evote.utils;

import java.util.Date;
import java.util.List;

public interface ElectionDataIF {

    public String getName();

    public String getDescription();

    public VotingSystemType getVotingSystem();

    public String[] getCandidates();

    public String[] getCandidateDescriptions();

    public Date getStartDate();

    public Date getEndDate();

    public ElectionEndCondition getEndCondition();

    public int getVoterCount();
}
