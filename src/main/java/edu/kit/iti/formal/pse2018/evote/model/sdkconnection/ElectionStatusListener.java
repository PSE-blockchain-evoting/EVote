/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.Transaction;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigResourceBundle;

import java.util.EnumSet;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import org.hyperledger.fabric.sdk.BlockEvent;
import org.hyperledger.fabric.sdk.BlockInfo;
import org.hyperledger.fabric.sdk.ChaincodeEvent;
import org.hyperledger.fabric.sdk.ChaincodeEventListener;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.Peer;
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
        //channel.registerChaincodeEventListener(Pattern.compile(bundle.getString("chaincode_name")),
        //        Pattern.compile(bundle.getString("chaincode_event_name")), this);
        System.out.println("\u001B[95m" + "EVENT LISTENER REGISTERED" + "\u001B[0m");
        System.out.println(channel.getEventHubs().iterator().next());
        channel.registerBlockListener(blockEvent -> {
            for (BlockEvent.TransactionEvent t : blockEvent.getTransactionEvents()) {
                System.out.println("\u001B[95m" + "BEVENT: " + "\u001B[0m");
                for (BlockInfo.TransactionEnvelopeInfo.TransactionActionInfo i: t.getTransactionActionInfos()) {
                    if (i != null && i.getEvent() != null && i.getEvent().getEventName() != null)
                        System.out.println("\u001B[95m" + "\t" + i.getEvent().getEventName() + "\u001B[0m");
                }
            }
        });
        channel.registerChaincodeEventListener(Pattern.compile(".*"), Pattern.compile(".*"), this);
    }

    /**
     * Receives chaincode event and calls onElectionEnd if the election is over, onElectionRunning otherwise.
     */
    public void received(String handle, BlockEvent blockEvent, ChaincodeEvent chaincodeEvent) {
        System.out.println("\u001B[95m" + "EVENT: " + chaincodeEvent.getEventName() + "\u001B[0m");
        if (chaincodeEvent.getPayload()[0] == 1) {
            sdkEventListener.onElectionEnd();
        } else {
            sdkEventListener.onElectionRunning();
        }
    }
}
