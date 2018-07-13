package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.model.ElectionData;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
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
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = Json.createWriter(stringWriter);
        JsonObject obj = ElectionData.toJson(this.electionData);
        writer.writeObject(obj);
        writer.close();
        return new String[]{stringWriter.toString()};
    }

    @Override
    protected String getFunctionName() {
        return "initializationInvokation";
    }
}
