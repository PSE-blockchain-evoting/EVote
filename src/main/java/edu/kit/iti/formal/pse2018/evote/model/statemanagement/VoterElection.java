package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.ElectionRunningException;
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
    private boolean hasVoted;


    /**
     * Initializes VoterElection. This provides very basic offline functionality and the voter should authenticate
     * to unlock most of the provided features.
     *
     * @param electionStatusListener the StatusListener for the View about Election updates.
     * @throws NetworkException If a Network Issue occurs.
     * @throws NetworkConfigException If the Network wasn't configured properly.
     */
    public VoterElection(ElectionStatusListener electionStatusListener) throws NetworkException,
            NetworkConfigException {
        super(electionStatusListener);
        vote = null;
        hasVoted = false;
    }

    @Override
    public String getOwnVote() {
        return vote;
    }


    @Override
    public boolean vote(String vote) throws NetworkException, NetworkConfigException {
        voterSDKInterface.vote(vote);
        this.vote = vote;
        return true;
    }

    @Override
    public boolean hasVoted() {
        return hasVoted;
    }

    @Override
    public void authenticate(String path) throws NetworkException, AuthenticationException,
            InternalSDKException, NetworkConfigException, WrongCandidateNameException,
            LoadVoteException, ElectionRunningException {
        voterSDKInterface = new VoterSDKInterfaceImpl(path, sdkEventListenerImpl);
        sdkInterfaceImpl = voterSDKInterface;
        if (sdkInterfaceImpl.isElectionInitialized()) {
            loadSDKData();
            String ownVote = voterSDKInterface.getOwnVote();
            if (ownVote.equals("")) {
                hasVoted = false;
            } else {
                hasVoted = true;
                vote = ownVote;
            }
            sdkEventListenerImpl.start();
        } else {
            throw new ElectionRunningException("No Election is initialized");
        }
    }
}
