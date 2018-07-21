package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

public class RelativeMajorityVotingSystem extends MajorityVotingSystem {

    public RelativeMajorityVotingSystem(Election election) {
        super(election);
    }

    @Override
    String determineWinner() {
        int[] voteCount = determineResults();
        int winnerIndex = 0;
        for (int i = 0; i < voteCount.length - 2; i++) {
            if (voteCount[i] < voteCount[i + 1]) {
                winnerIndex = i + 1;
            }
        }
        return election.candidateList[winnerIndex].getName();
    }
}