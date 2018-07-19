package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;

public class RelativeMajorityVotingSystem extends MajorityVotingSystem {

    public RelativeMajorityVotingSystem(Election election) {
        super(election);
    }

    @Override
    Candidate determineWinner() throws NetworkException, NetworkConfigException {
        int[] voteCount = determineResults();
        int winnerIndex = 0;
        for (int i = 0; i < voteCount.length - 2; i++) {
            if (voteCount[i] < voteCount[i + 1]) {
                winnerIndex = i + 1;
            }
        }
        return election.candidateList[winnerIndex];
    }
}
