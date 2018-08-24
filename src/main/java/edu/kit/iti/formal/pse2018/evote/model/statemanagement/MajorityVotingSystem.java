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

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


public abstract class MajorityVotingSystem extends VotingSystem {

    int[] votes;

    public MajorityVotingSystem(Election election) {
        super(election);
        votes = new int[election.getElectionData().getCandidates().length];
    }

    @Override
    public void loadVote(String vote) {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(vote.getBytes(StandardCharsets.UTF_8)));
        JsonObject obj = reader.readObject();
        String candidate = obj.getString("candidate");

        String[] candidates = election.getElectionData().getCandidates();
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i].equals(candidate)) {
                ++votes[i];
            }
        }
    }

    @Override
    int[] determineResults() {
        return votes;
    }
}
