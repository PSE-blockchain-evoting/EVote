package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class QueryTransaction extends Transaction {

    public QueryTransaction(HFClient client) {
        super(client);
    }

    protected abstract void parseResultString(String result);

    public void query() {

    }
}
