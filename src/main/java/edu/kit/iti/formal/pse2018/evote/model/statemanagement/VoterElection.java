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

package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.ElectionRunningException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.VoterSDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.VoterViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.VoterSDKInterfaceImpl;

public class VoterElection extends Election implements VoterViewToModelIF, VoterControlToModelIF {

    private VoterSDKInterface voterSDKInterface;
    private String vote;
    private boolean hasVoted;


    /**
     * Initializes VoterElection. This provides very basic offline functionality and the voter should authenticate
     * to unlock most of the provided features.
     *
     * @param electionStatusListener the StatusListener for the View about Election updates.
     * @throws NetworkException If a Network Issue occurs.
     * @throws NetworkConfigException If the Network wasn't configured properly.
     */
    public VoterElection(ElectionStatusListener electionStatusListener) throws NetworkException,
            NetworkConfigException {
        super(electionStatusListener);
        vote = null;
        hasVoted = false;
    }

    @Override
    public String getOwnVote() {
        return vote;
    }


    @Override
    public boolean vote(String vote) throws NetworkException, NetworkConfigException {
        voterSDKInterface.vote(vote);
        this.vote = vote;
        return true;
    }

    @Override
    public boolean hasVoted() {
        return hasVoted;
    }

    @Override
    public void authenticate(String path) throws NetworkException, AuthenticationException,
            InternalSDKException, NetworkConfigException, WrongCandidateNameException,
            ElectionRunningException {
        voterSDKInterface = new VoterSDKInterfaceImpl(path);
        sdkInterfaceImpl = voterSDKInterface;
        if (sdkInterfaceImpl.isElectionInitialized()) {
            loadSDKData();
            String ownVote = voterSDKInterface.getOwnVote();
            if (ownVote.equals("")) {
                hasVoted = false;
            } else {
                hasVoted = true;
                vote = ownVote;
            }
            statusThread.start();
        } else {
            throw new ElectionRunningException("No Election is initialized");
        }
    }
}
