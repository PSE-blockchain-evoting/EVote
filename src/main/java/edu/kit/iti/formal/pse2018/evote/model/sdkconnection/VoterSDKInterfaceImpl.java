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

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.VoterSDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.OwnVoteQuery;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.VoteInvocation;

import java.io.IOException;

import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;

public class VoterSDKInterfaceImpl extends SDKInterfaceImpl implements VoterSDKInterface {

    public VoterSDKInterfaceImpl(String filePath, SDKEventListener sdkEventListener) throws NetworkException,
            AuthenticationException, InternalSDKException, NetworkConfigException {
        super(filePath, sdkEventListener);
    }

    /**
     * Votes for the current User.
     * @param vote the vote to cast
     */
    public void vote(String vote) throws NetworkException, NetworkConfigException {
        VoteInvocation inv = new VoteInvocation(this.hfClient, vote);
        try {
            inv.invoke();
        } catch (ProposalException e) {
            throw new NetworkException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
    }

    /**
     * Gets the current users vote.
     * @return the vote
     */
    public String getOwnVote() throws NetworkException, NetworkConfigException {
        OwnVoteQuery query = new OwnVoteQuery(this.hfClient);
        try {
            query.query();
        } catch (ProposalException e) {
            throw new NetworkException(e.getMessage());
        } catch (InvalidArgumentException e) {
            throw new NetworkConfigException(e.getMessage());
        }
        return query.getResult();
    }
}
