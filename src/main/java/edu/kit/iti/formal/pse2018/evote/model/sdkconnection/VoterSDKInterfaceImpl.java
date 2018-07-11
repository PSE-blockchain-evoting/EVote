package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.VoterSDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.OwnVoteQuery;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.VoteInvocation;

import java.io.IOException;

import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;

public class VoterSDKInterfaceImpl extends SDKInterfaceImpl implements VoterSDKInterface {

    public VoterSDKInterfaceImpl(String filePath, SDKEventListener sdkEventListener) throws IOException,
            ClassNotFoundException {
        super(filePath, sdkEventListener);
    }

    /**
     * Votes for the current User.
     * @param vote the vote to cast
     */
    public void vote(String vote) {
        VoteInvocation inv = new VoteInvocation(this.hfClient, vote);
        try {
            inv.invoke();
        } catch (ProposalException e) {
            throw new RuntimeException(); //TODO: Replace with custom exception
        } catch (InvalidArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * Gets the current users vote.
     * @return the vote
     */
    public String getOwnVote() {
        OwnVoteQuery query = new OwnVoteQuery(this.hfClient);
        try {
            query.query();
        } catch (ProposalException e) {
            throw new RuntimeException(); //TODO: Replace with custom exception
        } catch (InvalidArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return query.getResult();
    }
}
