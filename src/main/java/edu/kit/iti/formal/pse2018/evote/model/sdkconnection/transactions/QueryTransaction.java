package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class QueryTransaction extends Transaction{

    public QueryTransaction(HFClient client, String[] args) {
        super(client, args);
    }

    protected abstract void parseResultString(String result);

    public void query() {

    }
}
