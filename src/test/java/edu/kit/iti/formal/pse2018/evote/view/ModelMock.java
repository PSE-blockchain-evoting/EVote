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

package edu.kit.iti.formal.pse2018.evote.view;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.VoterViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.utils.ConfigIssues;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.TimeOnlyCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

import java.util.Date;

public class ModelMock implements SupervisorControlToModelIF,
        SupervisorViewToModelIF, VoterViewToModelIF, VoterControlToModelIF {

    private String ownVote = null;

    public ModelMock(ElectionStatusListener l) throws NetworkException, NetworkConfigException{

    }

    @Override
    public void importConfig(String path) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void exportConfig(String path) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void setVoters(String[] names) {
    }

    @Override
    public boolean setElectionData(ElectionDataIF electionData) {
        return true;
    }

    @Override
    public boolean firstAuthentication(String username, String password) throws NetworkException,
            AuthenticationException, InternalSDKException, NetworkConfigException {
        return true;
    }

    @Override
    public String[] getVotes() {
        return null;
    }

    @Override
    public void startElection() throws NetworkException, NetworkConfigException {
    }

    @Override
    public void destroyElection() {
    }

    @Override
    public void authenticate(String path) throws NetworkException, AuthenticationException,
            InternalSDKException, NetworkConfigException {
    }

    @Override
    public ElectionDataIF getElectionData() {
        ElectionDataImpl data = new ElectionDataImpl();
        data.setName("Blockchain 2018");
        data.setDesc("Die wahl zur besten blockcahin kdkdkd");
        data.setType(VotingSystemType.ABSOLUTEMAJORITY);
        String[] candidates = {"Bitcoin", "Ethereum", "Monero", "sth else"};
        data.setCandidates(candidates);
        String[] desc = {"Die erste", "Die andere", "die geheime", "hab ich nie von gehört"};
        data.setCandidatesDesc(desc);
        data.setVoterCount(4);
        data.setStart(new Date(2001, 12, 22));
        data.setEnd(new Date());
        data.setEec(new TimeOnlyCondition());

        return data;
    }

    @Override
    public String getWinner() {
        return "Bitcoin";
    }

    @Override
    public boolean isElectionInitialized() throws NetworkException, NetworkConfigException {
        return false;
    }

    @Override
    public void setElectionStatusListener(ElectionStatusListener listener) {
    }

    @Override
    public int[] getResults() {
        //int[] i = {10, 2, 41, 5};
        int[] i = {4, 123, 64, 22, 5,
                      43, 83, 23, 12,
                      73, 22, 13, 2,
                      21, 22, 3, 140};
        return i;
    }

    @Override
    public String[] getVoters() {
        String[] voters = {"A", "BC", "DEF", "GESA"};
        return voters;
    }

    @Override
    public ConfigIssues getConfigIssues() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean vote(String vote) throws NetworkException, NetworkConfigException {
        ownVote = vote;
        return true;
    }

    @Override
    public boolean hasVoted() {
        return false;
    }

    @Override
    public String getOwnVote() {
        //return "{\"candidate\":\"Bitcoin\"}";
        return ownVote;
    }
}
