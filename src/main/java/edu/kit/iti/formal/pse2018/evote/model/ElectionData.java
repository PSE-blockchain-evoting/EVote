package edu.kit.iti.formal.pse2018.evote.model;

import edu.kit.iti.formal.pse2018.evote.utils.CandidatePercentileCondition;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionEndCondition;
import edu.kit.iti.formal.pse2018.evote.utils.TimeOnlyCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VoterPercentileCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

import java.util.Arrays;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

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

    /**
     * Creates a new JsonObject from any class implementing ElectionDataIF.
     * @param electionData the election data to marshall
     * @return JsonObject containing the election data
     */
    public static JsonObjectBuilder toJson(ElectionDataIF electionData) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", electionData.getName());
        objectBuilder.add("description", electionData.getDescription());
        objectBuilder.add("votingSystem", electionData.getVotingSystem().name());
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(Arrays.asList(electionData.getCandidates()));
        objectBuilder.add("candidates", arrayBuilder);
        arrayBuilder = Json.createArrayBuilder(Arrays.asList(electionData.getCandidateDescriptions()));
        objectBuilder.add("candidateDescriptions", arrayBuilder);
        objectBuilder.add("startDate", electionData.getStartDate().getTime() / 1000 * 1000);
        objectBuilder.add("endDate", electionData.getEndDate().getTime() / 1000 * 1000);
        JsonObjectBuilder temp = Json.createObjectBuilder();
        objectBuilder.add("endCondition", electionData.getEndCondition().asJsonObject());
        objectBuilder.add("voterCount", electionData.getVoterCount());
        return objectBuilder;
    }

    /**
     * Creates a new ElectionData object from the given JsonObject.
     * @param obj the json object to unpack
     * @return new ElectionData object
     */
    public static ElectionDataIF fromJSon(JsonObject obj) {
        String name = obj.getString("name");
        String description = obj.getString("description");
        VotingSystemType votingSystemType = VotingSystemType.valueOf(obj.getString("votingSystem"));
        String[] candidates = obj.getJsonArray("candidates").getValuesAs(
            jsonValue -> jsonValue.toString().replaceAll("\"", "")).toArray(new String[0]);

        String[] candidateDescs = obj.getJsonArray("candidateDescriptions").getValuesAs(
            jsonValue -> jsonValue.toString().replaceAll("\"", "")).toArray(new String[0]);
        Date startDate = new Date(obj.getJsonNumber("startDate").longValue());
        Date endDate = new Date(obj.getJsonNumber("endDate").longValue());
        int voterCount = obj.getInt("voterCount");
        obj = obj.getJsonObject("endCondition");
        ElectionEndCondition condition;
        switch (obj.getString("type")) {
            case "VoterPercentileCondition":
                condition = new VoterPercentileCondition(obj.getInt("percentage"));
                break;
            case "TimeOnlyCondition":
                condition = new TimeOnlyCondition();
                break;
            case "CandidatePercentileCondition":
                condition = new CandidatePercentileCondition(obj.getInt("percentage"));
                break;
            default:
                throw new RuntimeException("No valid end condition"); //TODO: Replace with proper exception
        }
        return new ElectionData(name, description, votingSystemType, candidates, candidateDescs, startDate,
                endDate, condition, voterCount);
    }
}
