package edu.kit.iti.formal.pse2018.evote.model;

public interface VoterSDKInterface extends SDKInterface {

    public void vote(String vote);

    public String getOwnVote();
}
