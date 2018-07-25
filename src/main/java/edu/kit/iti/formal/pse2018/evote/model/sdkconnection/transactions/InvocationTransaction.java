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

package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigResourceBundle;

import java.util.Collection;
import java.util.ResourceBundle;

import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.ChaincodeResponse;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;

/**
 * Base class for transactions which change data on the ledger.
 */
public abstract class InvocationTransaction extends Transaction {

    public InvocationTransaction(HFClient client) {
        super(client);
    }

    /**
     * Runs the invocation.
     * @throws InvalidArgumentException @see Hyperledger
     * @throws ProposalException @see Hyperledger
     */
    public void invoke() throws InvalidArgumentException, ProposalException, NetworkException {
        System.out.println("\u001B[34m" + "INVOKE: " + getFunctionName() + "\u001B[0m");
        ResourceBundle bundle = ConfigResourceBundle.loadBundle("config");
        Channel channel = this.client.getChannel(bundle.getString("channel_name"));
        TransactionProposalRequest request = client.newTransactionProposalRequest();
        ChaincodeID chaincodeID = ChaincodeID.newBuilder().setName(bundle.getString("chaincode_name")).build();
        request.setChaincodeID(chaincodeID);
        request.setFcn(getFunctionName());
        request.setArgs(buildArgumentStrings());
        Collection<ProposalResponse> responses = channel.sendTransactionProposal(request);
        for (ProposalResponse resp : responses) {
            if (!resp.isVerified() ||  resp.getStatus() != ChaincodeResponse.Status.SUCCESS) {
                throw new NetworkException(resp.getMessage());
            }
        }
        channel.sendTransaction(responses).join();
    }
}
