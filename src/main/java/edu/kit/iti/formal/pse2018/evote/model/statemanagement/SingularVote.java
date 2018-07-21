package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class SingularVote extends Vote {

    private Candidate candidateVote; //TODO: NEEDS TO BE SET

    public SingularVote(String vote) { //TODO: CHECK IF THIS IS CORRECT - SEE SUPER-CONSTRUCTOR
        super(vote);
    }

    @Override
    String asString() {
        return null;
    }

    /**
     * converts a JSON-String into a SingularVote instance.
     * @param vote the JSON String
     * @return SingularVote containing representing the vote
     */
    public static SingularVote loadVote(String vote) {
        JsonReader reader = Json.createReader(new ByteArrayInputStream(vote.getBytes(StandardCharsets.UTF_8)));
        JsonObject obj = reader.readObject();
        String candidate = obj.getString("candidate");
        return new SingularVote(candidate);
    }
}
