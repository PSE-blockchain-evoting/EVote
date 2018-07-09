package edu.kit.iti.formal.pse2018.evote.utils;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

public class VoterPercentileCondition extends ElectionEndCondition {

    private int percentage;

    public VoterPercentileCondition(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String asString() {
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        JsonObject endCondition = Json.createObjectBuilder().add("type", "VoterPercentileCondition")
                .add("percentage", this.percentage).build();
        writer.writeObject(endCondition);
        writer.close();
        return stringWriter.toString();
    }
}
