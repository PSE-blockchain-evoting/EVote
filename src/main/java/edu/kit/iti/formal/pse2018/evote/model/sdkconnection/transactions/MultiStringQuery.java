package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.model.ElectionData;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class MultiStringQuery extends QueryTransaction {

    private String[] result;

    public MultiStringQuery(HFClient client) {
        super(client);
    }

    @Override
    protected void parseResultString(String result) {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8)));
        JsonArray arr = reader.readArray();
        this.result = arr.getValuesAs(jsonValue -> jsonValue.toString().replaceAll("\"", ""))
                .toArray(new String[0]);
    }

    public String[] getResult() {
        return this.result;
    }
}
