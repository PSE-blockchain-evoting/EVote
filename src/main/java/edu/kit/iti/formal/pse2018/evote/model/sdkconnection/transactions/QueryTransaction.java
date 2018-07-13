package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;

import java.util.Collection;
import java.util.ResourceBundle;

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
        if (responses.stream().anyMatch(proposalResponse ->
                proposalResponse.getStatus() != ChaincodeResponse.Status.SUCCESS)) {
            throw new NetworkException("Query proposal failed");
        }
        parseResultString(responses.iterator().next().getProposalResponse().getPayload().toStringUtf8());
    }
}
