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

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RelativeMajorityVotingTest {

    @Test
    public void all(){
        Locale.setDefault(new Locale("de", "DE"));

        String[] candidates = new String[]{"Albert", "Johanna", "Karl"};

        ElectionDataIF electionData = mock(ElectionDataIF.class);
        when(electionData.getCandidates()).thenReturn(candidates);
        Election election = mock(Election.class);
        when(election.getElectionData()).thenReturn(electionData);

        RelativeMajorityVotingSystem relativeMajorityVotingSystem = new RelativeMajorityVotingSystem(election);

        relativeMajorityVotingSystem.loadVote("{\"candidate\":\"Karl\"}");
        relativeMajorityVotingSystem.loadVote("{\"candidate\":\"Albert\"}");

        //no single relative majority
        String winner = relativeMajorityVotingSystem.determineWinner();
        assertEquals(null, winner);

        relativeMajorityVotingSystem.loadVote("{\"candidate\":\"Karl\"}");
        relativeMajorityVotingSystem.loadVote("{\"candidate\":\"Johanna\"}");
        //karl wins by relative majority
        winner = relativeMajorityVotingSystem.determineWinner();
        assertEquals("Karl", winner);
    }
}
