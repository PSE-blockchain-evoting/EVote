package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionEndCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

import java.util.Date;

/**
 * Data Transfer Object for metadata pertaining a election.
 */
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

    /**
     * Creates a new ElectionData object.
     * @param name election name
     * @param description election description
     * @param votingSystem election voting system
     * @param candidates election candidate names
     * @param candidateDescriptions election candidate descriptions
     * @param startDate election start date
     * @param endDate election end date
     * @param endCondition additional end condition
     * @param voterCount number of voters
     */
    public ElectionData(String name, String description, VotingSystemType votingSystem, String[] candidates,
                        String[] candidateDescriptions, Date startDate, Date endDate,
                        ElectionEndCondition endCondition, int voterCount) {
        this.name = name;
        this.description = description;
        this.votingSystem = votingSystem;
        this.candidates = candidates;
        this.candidateDescriptions = candidateDescriptions;
        this.startDate = startDate;
        this.endDate = endDate;
        this.endCondition = endCondition;
        this.voterCount = voterCount;
    }

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
