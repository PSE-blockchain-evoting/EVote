package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class Transaction {

    protected HFClient client;

    protected Transaction(HFClient client) {
        this.client = client;
    }

    protected abstract String[] buildArgumentStrings();

    protected abstract String getFunctionName();
}
