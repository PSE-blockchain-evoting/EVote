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

package edu.kit.iti.formal.pse2018.evote.view.supervisorview;

import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

public abstract class SupervisorVSComponentManagerBuilder {

    private SupervisorVSComponentManagerBuilder() {

    }

    /**
     * Builds the correct SupervisorVSComponentManager based on the Voting System.
     * @param system The VotingSystem.
     * @param adapter The context where the ComponentManager is needed.
     * @return A SupervisorVSComponentManager instance.
     */
    public static SupervisorVSComponentManager generateComponentManager(
            VotingSystemType system, SupervisorAdapter adapter) {
        switch (system) {
            case ABSOLUTEMAJORITY:
            case RELATIVEMAJORITY:
                return new SupervisorMajorityVotingComponentManager(adapter);
            case INSTANTRUNOFF:
                return new SupervisorIRVComponentManager(adapter);
            default:
                throw new IllegalArgumentException("Unknown Voting System type");
        }
    }

}
