package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.VoterSDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.OwnVoteQuery;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.VoteInvocation;

import java.io.IOException;

import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;

public class VoterSDKInterfaceImpl extends SDKInterfaceImpl implements VoterSDKInterface {

    public VoterSDKInterfaceImpl(String filePath, SDKEventListener sdkEventListener) throws IOException,
            ClassNotFoundException {
        super(filePath, sdkEventListener);
    }

    /**
     * Votes for the current User.
     * @param vote the vote to cast
     * @return true if voting was successful
     */
    public boolean vote(String vote) {
        VoteInvocation inv = new VoteInvocation(this.hfClient, vote);
        inv.invoke();
        return true; //TODO: Meaningful return value

    }

    /**
     * Gets the current users vote.
     * @return the vote
     */
    public String getOwnVote() {
        OwnVoteQuery query = new OwnVoteQuery(this.hfClient);
        query.query();
        return query.getResult();
    }
}
