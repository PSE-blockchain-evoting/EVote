package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.ChaincodeResponse;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.QueryByChaincodeRequest;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;

/**
 * Base class for transactions which request data from the ledger.
 */
public abstract class QueryTransaction extends Transaction {

    public QueryTransaction(HFClient client) {
        super(client);
    }

    protected abstract void parseResultString(String result);

    /**
     * Queries the ledger through the peer(s).
     * @throws ProposalException @see Hyperledger
     * @throws InvalidArgumentException @see Hyperledger
     */
    public void query() throws ProposalException, InvalidArgumentException, NetworkException {
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        Channel channel = this.client.getChannel(bundle.getString("channel_name"));
        QueryByChaincodeRequest request = client.newQueryProposalRequest();
        ChaincodeID chaincodeID = ChaincodeID.newBuilder().setName(bundle.getString("chaincode_name")).build();
        request.setChaincodeID(chaincodeID);
        request.setFcn(getFunctionName());
        request.setArgs(buildArgumentStrings());
        Collection<ProposalResponse> responses = channel.queryByChaincode(request);
        for (ProposalResponse resp : responses) {
            if (resp.getStatus() != ChaincodeResponse.Status.SUCCESS) {
                throw new NetworkException(String.format("Query proposal failed. Peer %s responded with %s",
                        resp.getPeer(), resp.getMessage()));
            }
        }
        parseResultString(new String(responses.iterator().next().getChaincodeActionResponsePayload()));
    }
}
