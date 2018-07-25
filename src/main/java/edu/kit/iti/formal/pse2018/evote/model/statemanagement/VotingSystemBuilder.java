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

import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

public class VotingSystemBuilder {

    private VotingSystemBuilder() {
    }

    /**
     * this method generates a voting system depending on the {@code VotingSystemType} that is passed as argument.
     * @param votingSystemType determines which voting system object is created
     * @param election the interfaces that contains the elections meta-data
     * @return
     */
    public static VotingSystem generateVotingSystem(VotingSystemType votingSystemType, Election election) {
        switch (votingSystemType) {
            case INSTANTRUNOFF: return new IRVVotingSystem(election);
            case ABSOLUTEMAJORITY: return new AbsoluteMajorityVotingSystem(election);
            case RELATIVEMAJORITY: return new RelativeMajorityVotingSystem(election);
            default: throw new IllegalArgumentException("Unknown Voting System: " + votingSystemType.name());
        }
    }
}
