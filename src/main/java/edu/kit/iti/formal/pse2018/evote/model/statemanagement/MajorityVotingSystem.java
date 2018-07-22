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
        votes = new int[election.electionDataIF.getCandidates().length];
    }

    @Override
    public void loadVote(String vote) {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(vote.getBytes(StandardCharsets.UTF_8)));
        JsonObject obj = reader.readObject();
        String candidate = obj.getString("candidate");

        String[] candidates = election.electionDataIF.getCandidates();
        for (int i = 0; i < candidate.length(); i++) {
            if (candidates[i].equals(candidate)) {
                votes[i]++;
            }
        }
    }

    @Override
    int[] determineResults() {
        return votes;
    }
}
