package edu.kit.iti.formal.pse2018.evote.view;

public interface VoterControlToViewIF extends ControlToViewIF {

    public void showWait();

    public void showChoice();

    public String getVote();
}
