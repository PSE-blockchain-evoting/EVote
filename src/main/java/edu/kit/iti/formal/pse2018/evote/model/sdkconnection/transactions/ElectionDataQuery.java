package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import org.hyperledger.fabric.sdk.HFClient;

public class ElectionDataQuery extends ElectionDataTypeQuery {

    public ElectionDataQuery(HFClient client) {
        super(client);
    }

    @Override
    protected String[] buildArgumentStrings() {
        return new String[0];
    }

    @Override
    protected String getFunctionName() {
        return null;
    }
}
