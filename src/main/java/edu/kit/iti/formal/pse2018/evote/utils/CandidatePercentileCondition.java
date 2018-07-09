package edu.kit.iti.formal.pse2018.evote.utils;

import javax.json.Json;
import javax.json.JsonObject;

public class CandidatePercentileCondition extends ElectionEndCondition {

    private int percentage;

    public CandidatePercentileCondition(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public JsonObject asJsonObject() {
        return Json.createObjectBuilder()
                .add("type", "CandidatePercentileCondition")
                .add("percentage", this.percentage)
                .build();
    }
}
