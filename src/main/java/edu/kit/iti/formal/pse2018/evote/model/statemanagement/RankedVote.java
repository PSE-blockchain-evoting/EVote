package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;

public class RankedVote extends Vote {
    //private static String[] candidates;

    private String[] candidates;

    /**
     * Save list of candidate names
     */
    /*
    public static void setCandidates(String[] candidates) {
        RankedVote.candidates = candidates;
    }
    */

    @Override
    String asString() {
        JsonArrayBuilder b = Json.createArrayBuilder();
        for (int i = 0; i < preferences.size(); i++) {
            b.add(candidates[preferences.get(i)]);
        }
        return b.build().toString();
    }

    /**
    * Candidates in ballot: from rank #1 to last rank.
    * preferences[0] is the number of candidate with the rank #1.
    * preferences[1] is the number of candidate with the rank #2.
    * etc.
    */
    private List<Integer> preferences = new LinkedList<Integer>();

    /**
    * Create Vote with preferences from rank #1 to rank #N.
    */
    public RankedVote(String[] candidates, Collection<Integer> preferences) {
        super("");
        this.candidates = candidates;
        this.preferences.addAll(preferences);
    }

    /**
    * Remove candidate from preferences list.
    */
    public void removeCandidate(int candInd) {
        this.preferences.removeAll(Collections.singleton(candInd));
    }

    /**
    * true if preferences list is empty.
    */
    public boolean isEmpty() {
        return this.preferences.size() == 0;
    }

    /**
    * Get the more preferable candidate.
    */
    public int getPreference() {
        return this.preferences.get(0);
    }

    /**
    * Copy this vote.
    */
    public RankedVote copy() {
        return new RankedVote(this.candidates, this.preferences);
    }
}
