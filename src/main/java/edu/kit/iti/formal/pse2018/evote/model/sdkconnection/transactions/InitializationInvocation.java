package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import org.hyperledger.fabric.sdk.HFClient;

public class InitializationInvocation extends InvocationTransaction {

    private ElectionDataIF electionData;

    public InitializationInvocation(HFClient client, ElectionDataIF electionData) {
        super(client);
        this.electionData = electionData;
    }

    @Override
    protected String[] buildArgumentStrings() {
        return new String[0]; //TODO: ElectionDataIF -> JSON-String
    }

    @Override
    protected String getFunctionName() {
        return null;
    }
}
