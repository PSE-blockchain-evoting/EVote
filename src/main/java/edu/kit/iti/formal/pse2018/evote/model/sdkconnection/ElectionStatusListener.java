package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import org.hyperledger.fabric.sdk.BlockEvent;
import org.hyperledger.fabric.sdk.ChaincodeEvent;
import org.hyperledger.fabric.sdk.ChaincodeEventListener;

public class ElectionStatusListener implements ChaincodeEventListener {

    //TODO: Add SDKEventListener


    public ElectionStatusListener() {
    }


    public void received(String handle, BlockEvent blockEvent, ChaincodeEvent chaincodeEvent) {
        //TODO: Redirect event
    }
}
