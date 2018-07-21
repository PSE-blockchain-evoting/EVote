package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.VoterSDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.VoterViewToModelIF;

public class VoterElection extends Election implements VoterViewToModelIF, VoterControlToModelIF {

    private VoterSDKInterface voterSDKInterface;
    private Vote vote;


    public VoterElection(ElectionStatusListener electionStatusListener) {
        super(electionStatusListener);
    }

    @Override
    public String getOwnVote() {
        return vote.asString();
    }


    @Override
    public boolean vote(String vote) throws NetworkException, NetworkConfigException {
        voterSDKInterface.vote(vote);
        return true;
    }

    @Override
    public void authenticate(String path) {
        //TODO
    }
}