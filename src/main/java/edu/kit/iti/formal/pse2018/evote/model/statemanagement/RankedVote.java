package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import java.util.LinkedList;
import java.util.List;

public class RankedVote extends Vote {

    private List<Candidate> candidateList = new LinkedList<>(); //TODO: NEEDS TO BE SET

    public RankedVote(String vote) { //TODO: IS THIS CORRECT? CHECK THE SUPER-CONSTRUCTOR
        super(vote);
    }
    @Override
    String asString() {
        return null;
    }

    public RankedVote loadVote(String vote){
        //TODO: IMPLEMENT
        return null;
    }
}
