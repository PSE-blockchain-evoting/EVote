package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

public class Candidate {

    private String name;
    private String description;

    public Candidate(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
