package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.StringWriter;
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import org.hyperledger.fabric.sdk.HFClient;

public class InitializationInvocation extends InvocationTransaction {

    private ElectionDataIF electionData;

    public InitializationInvocation(HFClient client, ElectionDataIF electionData) {
        super(client);
        this.electionData = electionData;
    }

    @Override
    protected String[] buildArgumentStrings() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", electionData.getName());
        objectBuilder.add("description", electionData.getDescription());
        objectBuilder.add("votingSystem", electionData.getVotingSystem().name());
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(Arrays.asList(electionData.getCandidates()));
        objectBuilder.add("candidates", arrayBuilder);
        arrayBuilder = Json.createArrayBuilder(Arrays.asList(electionData.getCandidateDescriptions()));
        objectBuilder.add("candidateDescriptions", arrayBuilder);
        objectBuilder.add("startDate", Long.toString(electionData.getStartDate().getTime() / 1000 * 1000));
        objectBuilder.add("endDate", Long.toString(electionData.getEndDate().getTime() / 1000 * 1000));
        JsonObjectBuilder temp = Json.createObjectBuilder();
        objectBuilder.add("endCondition", electionData.getEndCondition().asJsonObject());
        objectBuilder.add("voterCount", electionData.getVoterCount());
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        writer.writeObject(objectBuilder.build());
        writer.close();
        return new String[]{stringWriter.toString()};
    }

    @Override
    protected String getFunctionName() {
        return "initializationInvokation";
    }
}
