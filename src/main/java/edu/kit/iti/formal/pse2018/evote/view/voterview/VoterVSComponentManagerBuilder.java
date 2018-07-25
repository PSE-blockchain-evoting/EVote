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

package edu.kit.iti.formal.pse2018.evote.view.voterview;

import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

public class VoterVSComponentManagerBuilder {

    private VoterVSComponentManagerBuilder() {

    }

    /**
     * Builds the correct VoterVSComponentManager based on the Voting System.
     * @param system The VotingSystem.
     * @param adapter The context where the ComponentManager is needed.
     * @return A VoterVSComponentManager instance.
     */
    public static VoterVSComponentManager generateComponentManager(
            VotingSystemType system, VoterAdapter adapter) {
        switch (system) {
            case ABSOLUTEMAJORITY:
            case RELATIVEMAJORITY:
                return new VoterMajorityVotingSystemComponentManager(adapter);
            case INSTANTRUNOFF:
                return new VoterIRVComponentManager(adapter);
            default:
                throw new IllegalArgumentException("Unknown Voting System type");
        }
    }
}
