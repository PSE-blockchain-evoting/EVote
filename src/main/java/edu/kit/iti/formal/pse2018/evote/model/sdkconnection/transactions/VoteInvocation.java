package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import org.hyperledger.fabric.sdk.HFClient;

public class VoteInvocation extends InvocationTransaction {

    private String vote;

    public VoteInvocation(HFClient client, String vote) {
        super(client);
        this.vote = vote;
    }

    @Override
    protected String[] buildArgumentStrings() {
        return new String[]{ this.vote };
    }

    @Override
    protected String getFunctionName() {
        return null;
    }
}
