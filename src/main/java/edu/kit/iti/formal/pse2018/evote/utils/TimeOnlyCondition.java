package edu.kit.iti.formal.pse2018.evote.utils;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

public class TimeOnlyCondition extends ElectionEndCondition {

    @Override
    public String asString() {
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        JsonObject endCondition = Json.createObjectBuilder().add("type", "TimeOnlyCondition")
                .build();
        writer.writeObject(endCondition);
        writer.close();
        return stringWriter.toString();
    }
}
