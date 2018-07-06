package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

public class SingularVote extends Vote {

    private Candidate candidateVote; //TODO: NEEDS TO BE SET

    public SingularVote(String vote) { //TODO: CHECK IF THIS IS CORRECT - SEE SUPER-CONSTRUCTOR
        super(vote);
    }
    @Override
    String asString() {
        return null;
    }

    public SingularVote loadVote(String vote){
        //TODO: IMPLEMENT
        return null;
    }
}
