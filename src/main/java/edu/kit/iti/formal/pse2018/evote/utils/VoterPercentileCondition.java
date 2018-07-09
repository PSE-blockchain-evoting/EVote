package edu.kit.iti.formal.pse2018.evote.utils;

import javax.json.Json;
import javax.json.JsonObject;

public class VoterPercentileCondition extends ElectionEndCondition {

    private int percentage;

    public VoterPercentileCondition(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public JsonObject asJsonObject() {
        return Json.createObjectBuilder()
                .add("type", "VoterPercentileCondition")
                .add("percentage", this.percentage)
                .build();
    }
}
