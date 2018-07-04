package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import org.hyperledger.fabric.sdk.HFClient;

public class DestructionInvocation extends InvocationTransaction {

    public DestructionInvocation(HFClient client) {
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
