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

import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;


public class IRVVotingSystem extends VotingSystem {
    /**
    * All Candidate Names.
    */
    private String[] candidates;

    /**
    * Scores of each candidate at some round.
    */
    private int[] scores;

    /**
    * Who is still in competition.
    */
    private boolean[] stillIn;

    /**
    * votes[i] is the vote with preferences of voter #i.
    */
    private List<RankedVote> votes = new LinkedList<RankedVote>();


    /**
     * create instance of IRVVotingSystem.
     * @param election Election with participating Candidates
     */
    public IRVVotingSystem(Election election) {
        super(election);
        candidates = election.getElectionData().getCandidates();
        int candCnt = candidates.length;
        scores = new int[candCnt];
        stillIn = new boolean[candCnt];
    }

    private void zeroScores() {
        for (int i = 0; i < scores.length; i++) {
            scores[i] = 0;
        }
    }

    private void makeAllStillIn() {
        for (int i = 0; i < stillIn.length; i++) {
            stillIn[i] = true;
        }
    }

    /**
    * Copy all votes and returns them.
    */
    private List<RankedVote> copyVotes() {
        List<RankedVote> res = new LinkedList<RankedVote>();
        int votesCnt = votes.size();
        for (int i = 0; i < votesCnt; i++) {
            res.add(this.votes.get(i).copy());
        }
        return res;
    }

    @Override
    public void loadVote(String vote) throws WrongCandidateNameException {
        ResourceBundle lang = ResourceBundle.getBundle("StateManagement");
        JsonReader reader = Json.createReader(new ByteArrayInputStream(vote.getBytes(StandardCharsets.UTF_8)));
        JsonArray obj = reader.readArray();
        String[] rankedCandidates = obj.getValuesAs(
            jsonValue -> jsonValue.toString().replaceAll("\"", "")).toArray(new String[0]);
        List<Integer> preferences = new ArrayList<Integer>();
        for (int i = 0; i < rankedCandidates.length; i++) {
            preferences.add(getCandidateIndex(rankedCandidates[i]));
        }
        RankedVote res = new RankedVote(this.candidates, preferences);
        this.votes.add(res);
    }

    /**
     * Get Candidate index not to store names in votes.
     */
    private int getCandidateIndex(String candidateName) throws WrongCandidateNameException {
        ResourceBundle lang = ResourceBundle.getBundle("StateManagement");
        for (int i = 0; i < this.candidates.length; i++) {
            if (this.candidates[i].equals(candidateName)) {
                return i;
            }
        }
        // "Candidate with the name %s was not found in list of candidates."
        throw new WrongCandidateNameException(String.format(lang.getString("candidateNotFound"), candidateName));
    }

    /**
     * Is there somebody who is still in the competition.
     * If no, then returns true. Otherwize, false.
     */
    private boolean isEmpty() {
        for (int i = 0; i < stillIn.length; i++) {
            if (stillIn[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get max score through still-in-candidates.
     */
    private int maxScoreIndex() {
        int res = -1;
        int resInd = -1;
        for (int i = 0; i < this.stillIn.length; i++) {
            if (this.stillIn[i] && this.scores[i] > res) {
                res = this.scores[i];
                resInd = i;
            }
        }
        return resInd;
    }

    /**
     * Get min score through still-in-candidates.
     */
    private int minScoreIndex() {
        int res = -1;
        int resInd = -1;
        for (int i = 0; i < this.stillIn.length; i++) {
            if (this.stillIn[i] && (this.scores[i] < res || res == -1)) {
                res = this.scores[i];
                resInd = i;
            }
        }
        return resInd;
    }

    @Override
    public String determineWinner() {
        // Work with copy, because we should to eliminate candidates
        // while determining the winner
        List<RankedVote> votesCpy = copyVotes();
        int voteCnt = votesCpy.size();
        makeAllStillIn();
        int winnerInd = -1;
        int elimineeInd;
        boolean determined = false;
        while (!isEmpty()) {
            zeroScores();
            for (int i = 0; i < voteCnt; i++) {
                if (!votesCpy.get(i).isEmpty()) {
                    scores[votesCpy.get(i).getPreference()]++;
                }
            }
            winnerInd = maxScoreIndex();
            if (scores[winnerInd] > voteCnt / 2) {
                determined = true;
                break;
            }
            elimineeInd = minScoreIndex();
            stillIn[elimineeInd] = false;
            for (int i = 0; i < voteCnt; i++) {
                votesCpy.get(i).removeCandidate(elimineeInd);
            }
        }
        if (winnerInd < 0 || winnerInd >= this.candidates.length || !determined) {
            return null;
        }
        return this.candidates[winnerInd];
    }

    /**
     * Returns scores of each candidate after the last round.
     */
    @Override
    public int[] determineResults() {
        int[] result = new int[1 + this.candidates.length * this.candidates.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = 0;
        }
        result[0] = this.candidates.length;
        for (int j = 0; j < this.votes.size(); j++) {
            RankedVote vote = this.votes.get(j);
            for (int i = 0; i < this.candidates.length; i++) {
                int rank = vote.getRank(i);
                if (rank > 0) {
                    result[1 + (rank - 1) * this.candidates.length + i]++;
                }
            }
        }
        return result;
    }
}
