package edu.kit.iti.formal.pse2018.evote.utils;

import javax.json.Json;
import javax.json.JsonObject;

public class TimeOnlyCondition extends ElectionEndCondition {

    @Override
    public JsonObject asJsonObject() {
        return Json.createObjectBuilder()
                .add("type", "TimeOnlyCondition")
                .build();
    }
}
