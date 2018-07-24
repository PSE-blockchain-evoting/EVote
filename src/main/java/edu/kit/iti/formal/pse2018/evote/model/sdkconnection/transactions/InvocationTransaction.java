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
