package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.ElectionData;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class ElectionDataQuery extends QueryTransaction {

    private ElectionData result;

    public ElectionDataQuery(HFClient client, String[] args) {
        super(client, args);
    }

    @Override
    protected void parseResultString(String result) {

    }

    public ElectionData getResult() {
        return this.result;
    }
}
