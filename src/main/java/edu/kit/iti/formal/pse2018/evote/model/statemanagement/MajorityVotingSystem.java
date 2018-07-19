package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;


public abstract class MajorityVotingSystem extends VotingSystem {
    public MajorityVotingSystem(Election election) {
        super(election);
    }

    @Override
    public Vote loadVote(String vote) {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(vote.getBytes(StandardCharsets.UTF_8)));
        JsonObject obj = reader.readObject();
        String candidate = obj.getString("candidate"); //TODO: FUNKTIONIERT DAS KORREKT SO?

        return new SingularVote(candidate);
    }

    @Override
    public int[] determineResults() throws NetworkException, NetworkConfigException {
        String[] orderedCandidateArray = election.electionDataIF.getCandidates();

        String[] candidateVotes = election.getVotes();

        int[] result = new int[orderedCandidateArray.length];

        for (String candidateVote : candidateVotes) {
            for (int i = 0; i < orderedCandidateArray.length - 1; i++) {
                if (candidateVote.equals(orderedCandidateArray[i])) {
                    result[i]++;
                }
            }
        }
        return result;
    }


}
