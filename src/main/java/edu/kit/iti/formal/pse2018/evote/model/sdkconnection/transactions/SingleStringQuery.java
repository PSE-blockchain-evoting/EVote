package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class SingleStringQuery extends QueryTransaction {

    private String result;

    public SingleStringQuery(HFClient client, String[] args) {
        super(client, args);
    }

    @Override
    protected void parseResultString(String result) {

    }

    public String getResult() {
        return this.result;
    }
}
