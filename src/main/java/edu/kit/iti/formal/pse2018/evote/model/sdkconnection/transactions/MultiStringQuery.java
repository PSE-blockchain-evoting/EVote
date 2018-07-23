package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import org.hyperledger.fabric.sdk.HFClient;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import javax.json.JsonString;

public abstract class MultiStringQuery extends QueryTransaction {

    private String[] result;

    public MultiStringQuery(HFClient client) {
        super(client);
    }

    @Override
    protected void parseResultString(String result) {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(result.getBytes(StandardCharsets.UTF_8)));
        JsonArray arr = reader.readArray();
        this.result = arr.getValuesAs(JsonString.class).stream().map(x -> x.getString()).toArray(String[]::new);
    }

    public String[] getResult() {
        System.out.println("\u001B[32m" + "Multistringquery result = " + Arrays.toString(this.result) + "\u001B[0m");
        return this.result;
    }
}
