package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigResourceBundle;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.hyperledger.fabric.sdk.BlockEvent;
import org.hyperledger.fabric.sdk.ChaincodeEvent;
import org.hyperledger.fabric.sdk.ChaincodeEventListener;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;

/**
 * Chaincode Event Listener that redirects events based on content.
 */
public class ElectionStatusListener implements ChaincodeEventListener {

    private SDKEventListener sdkEventListener;

    /**
     * Creates a new ElectionStatusListener and registers it as chaincode listener.
     * @param sdkEventListener listener to redirect events to
     * @param channel channel to listen on
     */
    public ElectionStatusListener(SDKEventListener sdkEventListener, Channel channel) throws InvalidArgumentException {
        this.sdkEventListener = sdkEventListener;
        ResourceBundle bundle = ConfigResourceBundle.loadBundle("config");
        channel.registerChaincodeEventListener(Pattern.compile(bundle.getString("chaincode_name")),
                Pattern.compile(bundle.getString("chaincode_event_name")), this);
    }

    /**
     * Receives chaincode event and calls onElectionEnd if the election is over, onElectionRunning otherwise.
     */
    public void received(String handle, BlockEvent blockEvent, ChaincodeEvent chaincodeEvent) {
        if (chaincodeEvent.getPayload()[0] == 1) {
            sdkEventListener.onElectionEnd();
        } else {
            sdkEventListener.onElectionRunning();
        }
    }
}
