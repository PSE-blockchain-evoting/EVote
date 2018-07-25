package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigResourceBundle;

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
        System.out.println("\u001B[34m" + "QUERY: " + getFunctionName() + "\u001B[0m");
        ResourceBundle bundle = ConfigResourceBundle.loadBundle("config");
        Channel channel = this.client.getChannel(bundle.getString("channel_name"));
        QueryByChaincodeRequest request = client.newQueryProposalRequest();
        ChaincodeID chaincodeID = ChaincodeID.newBuilder().setName(bundle.getString("chaincode_name")).build();
        request.setChaincodeID(chaincodeID);
        request.setFcn(getFunctionName());
        request.setArgs(buildArgumentStrings());
        Collection<ProposalResponse> responses = channel.queryByChaincode(request);
        for (ProposalResponse resp : responses) {
            if (resp.getStatus() != ChaincodeResponse.Status.SUCCESS) {
                throw new NetworkException(resp.getMessage());
            }
        }
        String[] payloads = new String[responses.size()];
        int count = 0;
        for (ProposalResponse resp : responses) {
            payloads[count] = new String(resp.getChaincodeActionResponsePayload());
            count++;
        }

        if (Arrays.stream(payloads).distinct().count() > 1) {
            throw new NetworkException("Responses differ");
        }
        parseResultString(payloads[0]);
    }
}
