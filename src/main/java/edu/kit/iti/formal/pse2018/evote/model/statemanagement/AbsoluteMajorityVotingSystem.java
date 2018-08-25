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

public class AbsoluteMajorityVotingSystem extends MajorityVotingSystem {

    public AbsoluteMajorityVotingSystem(Election election) {
        super(election);
    }

    @Override
    String determineWinner() {
        int[] voteCount = determineResults();
        int winnerIndex = 0;
        int totalNumberOfVotes = 0;
        for (int i = 0; i < votes.length; i++) {
            totalNumberOfVotes += votes[i];
        }

        for (int i = 1; i < voteCount.length; i++) {
            winnerIndex = voteCount[i] > voteCount[winnerIndex] ? i : winnerIndex;
        }
        if ((double)voteCount[winnerIndex] > ((double) totalNumberOfVotes / 2)) {
            return election.getElectionData().getCandidates()[winnerIndex];
        }
        return null; //no winner
    }
}
