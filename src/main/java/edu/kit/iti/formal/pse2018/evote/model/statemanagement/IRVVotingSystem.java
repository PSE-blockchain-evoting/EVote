package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;

public class IRVVotingSystem extends VotingSystem {

    public IRVVotingSystem(Election election) {
        super(election);
    }

    @Override
    int[] determineResults() throws NetworkException, NetworkConfigException {
        return new int[0];
    }

    @Override
    Vote loadVote(String vote) {
        //TODO: IMPLEMENT
        return null;
    }

    @Override
    Candidate determineWinner() {
        //TODO: IMPLEMENT
        return null;
    }
}
