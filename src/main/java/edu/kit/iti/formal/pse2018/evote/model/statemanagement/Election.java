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

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SDKInterface;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;


public abstract class Election {

    protected SDKInterface sdkInterfaceImpl;

    protected SDKEventListenerImpl sdkEventListenerImpl;

    protected ElectionDataIF electionDataIF;

    private VotingSystem votingSystem;

    /**
     * Creates an instance of Election. Election is the heart of the model.
     * It manages the related data and logic for Election.
     *
     * @param electionStatusListener The GUI listener for election events.
     * @throws NetworkException       Exception within the SDKConnection is thrown.
     * @throws NetworkConfigException The network was wrongly configured.
     */
    public Election(ElectionStatusListener electionStatusListener) throws NetworkException, NetworkConfigException {
        sdkEventListenerImpl = new SDKEventListenerImpl(this);
        sdkEventListenerImpl.setElectionStatusListener(electionStatusListener);
    }

    public void setElectionStatusListener(ElectionStatusListener listener) {
        sdkEventListenerImpl.setElectionStatusListener(listener);
    }

    public ElectionDataIF getElectionData() {
        return electionDataIF;
    }

    /**
     * Starts a check, whether election is over.
     *
     * @throws NetworkException       Exception within the SDKConnection is thrown.
     * @throws NetworkConfigException The network was wrongly configured.
     */
    public void checkElectionOver() throws NetworkException, NetworkConfigException {
        if (sdkInterfaceImpl != null) {
            sdkInterfaceImpl.dispatchElectionOverCheck();
        }
    }

    public String getWinner() {
        return votingSystem.determineWinner();
    }

    /**
     * Returns the current Results of the running election.
     *
     * @return the result data.
     */
    public int[] getResults() {
        return votingSystem.determineResults();
    }

    protected void loadSDKData() throws NetworkException, NetworkConfigException,
            WrongCandidateNameException {

        assert (sdkInterfaceImpl != null);
        ElectionDataIF data = sdkInterfaceImpl.getElectionData();
        if (data != null) {
            electionDataIF = data;
            reloadVotes();
        }
    }

    protected void reloadVotes() throws NetworkException, NetworkConfigException,
            WrongCandidateNameException {
        votingSystem = VotingSystemBuilder.generateVotingSystem(electionDataIF.getVotingSystem(), this);
        String[] votes = sdkInterfaceImpl.getAllVotes();
        for (String vote : votes) {
            votingSystem.loadVote(vote);
        }

    }

    public boolean isElectionInitialized() throws NetworkException, NetworkConfigException {
        return sdkInterfaceImpl.isElectionInitialized();
    }
}
