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

import org.junit.Test;
import edu.kit.iti.formal.pse2018.evote.view.voterview.VoterIRVComponentManager;

import static org.junit.Assert.assertTrue;

public class VoterIRVComponentManagerTest {

    @Test
    public void simpleRanking(){
        String[] candidates = {"A", "B", "C", "D", "E", "F", "G"};
        int[] ranking = {0, 4, 0, 3, 1, 0, 2};

        String[] vote = VoterIRVComponentManager.evaluateInput(ranking, candidates);

        assertTrue(vote.length == 4);

        assertTrue(vote[0].equals("E"));
        assertTrue(vote[1].equals("G"));
        assertTrue(vote[2].equals("D"));
        assertTrue(vote[3].equals("B"));
    }

    @Test
    public void jumpingRanking(){
        String[] candidates = {"A", "B", "C", "D", "E", "F", "G"};
        int[] ranking = {0, 1, 0, 3, 6, 0, 10};

        String[] vote = VoterIRVComponentManager.evaluateInput(ranking, candidates);

        assertTrue(vote.length == 4);

        assertTrue(vote[0].equals("B"));
        assertTrue(vote[1].equals("D"));
        assertTrue(vote[2].equals("E"));
        assertTrue(vote[3].equals("G"));
    }

    @Test
    public void emptyRanking(){
        String[] candidates = {"A", "B", "C", "D", "E", "F", "G"};
        int[] ranking = {0, 0, 0, 0, 0, 0, 0};

        String[] vote = VoterIRVComponentManager.evaluateInput(ranking, candidates);

        assertTrue(vote.length == 0);
    }
}
