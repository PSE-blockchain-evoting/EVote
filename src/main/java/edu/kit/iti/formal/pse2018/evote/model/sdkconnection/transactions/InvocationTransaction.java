package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class InvocationTransaction extends Transaction {

    public InvocationTransaction(HFClient client, String[] args) {
        super(client, args);
    }

    public void invoke() {

    }
}
