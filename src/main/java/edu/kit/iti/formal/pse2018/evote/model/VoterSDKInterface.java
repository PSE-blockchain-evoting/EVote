package edu.kit.iti.formal.pse2018.evote.model;

public interface VoterSDKInterface extends SDKInterface {

    public boolean vote(String vote);

    public String getOwnVote();
}
