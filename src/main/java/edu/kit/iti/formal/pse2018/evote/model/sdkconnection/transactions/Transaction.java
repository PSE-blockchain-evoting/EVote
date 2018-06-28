package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class Transaction {

    protected String[] args;
    protected HFClient client;

    protected Transaction(HFClient client, String[] args) {
        this.client = client;
        this.args = args;
    }

    protected abstract String[] buildArgumentStrings();

    protected abstract String getFunctionName();
}
