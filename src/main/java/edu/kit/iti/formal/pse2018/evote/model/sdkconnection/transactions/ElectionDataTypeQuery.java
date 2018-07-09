package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.ElectionData;
import edu.kit.iti.formal.pse2018.evote.utils.CandidatePercentileCondition;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionEndCondition;
import edu.kit.iti.formal.pse2018.evote.utils.TimeOnlyCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VoterPercentileCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class ElectionDataTypeQuery extends QueryTransaction {

    private ElectionDataIF result;

    public ElectionDataTypeQuery(HFClient client) {
        super(client);
    }

    @Override
    protected void parseResultString(String result) {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8)));
        JsonObject obj = reader.readObject();
        String name = obj.getString("name");
        String description = obj.getString("description");
        VotingSystemType votingSystemType = VotingSystemType.valueOf(obj.getString("votingSystem"));
        String[] candidates = obj.getJsonArray("candidates").getValuesAs(
            jsonValue -> {
                return jsonValue.toString().replaceAll("\"", "");
            }).toArray(new String[0]);

        String[] candidateDescs = obj.getJsonArray("candidateDescriptions").getValuesAs(
            jsonValue -> {
                return jsonValue.toString().replaceAll("\"", "");
            }).toArray(new String[0]);
        Date startDate = new Date(Long.valueOf(obj.getString("startDate")));
        Date endDate = new Date(Long.valueOf(obj.getString("endDate")));
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
        this.result = new ElectionData(name, description, votingSystemType, candidates, candidateDescs, startDate,
                endDate, condition, voterCount);
    }

    public ElectionDataIF getResult() {
        return this.result;
    }
}
