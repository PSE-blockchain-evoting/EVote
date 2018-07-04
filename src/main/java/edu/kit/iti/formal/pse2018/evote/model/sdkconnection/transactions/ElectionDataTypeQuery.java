package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.ElectionData;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import org.hyperledger.fabric.sdk.HFClient;

public abstract class ElectionDataTypeQuery extends QueryTransaction {

    private ElectionDataIF result;

    public ElectionDataTypeQuery(HFClient client) {
        super(client);
    }

    @Override
    protected void parseResultString(String result) {

    }

    public ElectionDataIF getResult() {
        return this.result;
    }
}
