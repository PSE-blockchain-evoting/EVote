package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.LoadVoteException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.VoterSDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.VoterViewToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.VoterSDKInterfaceImpl;

public class VoterElection extends Election implements VoterViewToModelIF, VoterControlToModelIF {

    private VoterSDKInterface voterSDKInterface;
    private String vote;


    public VoterElection(ElectionStatusListener electionStatusListener) throws NetworkException,
            NetworkConfigException {
        super(electionStatusListener);
    }

    @Override
    public String getOwnVote() {
        return vote;
    }


    @Override
    public boolean vote(String vote) throws NetworkException, NetworkConfigException {
        voterSDKInterface.vote(vote);
        return true;
    }

    @Override
    public void authenticate(String path) throws NetworkException, AuthenticationException,
            InternalSDKException, NetworkConfigException, WrongCandidateNameException, LoadVoteException {
        voterSDKInterface = new VoterSDKInterfaceImpl(path, sdkEventListenerImpl);
        sdkInterfaceImpl = voterSDKInterface;
        loadSDKData();
        sdkEventListenerImpl.start();
    }
}
