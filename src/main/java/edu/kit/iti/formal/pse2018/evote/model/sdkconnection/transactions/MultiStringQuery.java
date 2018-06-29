package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class MultiStringQuery extends QueryTransaction {

    private String[] result;

    public MultiStringQuery(HFClient client) {
        super(client);
    }

    @Override
    protected void parseResultString(String result) {

    }

    public String[] getResult() {
        return this.result;
    }
}