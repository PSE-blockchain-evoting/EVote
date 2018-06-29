package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;

import java.io.IOException;

import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;

public class VoterSDKInterfaceImpl extends SDKInterfaceImpl {

    public VoterSDKInterfaceImpl(String filePath, SDKEventListener sdkEventListener) throws IOException,
            ClassNotFoundException {
        super(filePath, sdkEventListener);
    }

    public boolean vote(String vote) {
        return true;

    }

    public String getOwnVote() {
        return "";
    }
}
