package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

public class AbsoluteMajorityVotingSystem extends MajorityVotingSystem {

    public AbsoluteMajorityVotingSystem(Election election) {
        super(election);
    }

    @Override
    String determineWinner() {
        int[] voteCount = determineResults();
        int winnerIndex = 0;
        int totalNumberOfVotes = election.getVotes().length;
        for (int i = 0; i < voteCount.length - 2; i++) {
            if (voteCount[i] < voteCount[i + 1]) {
                winnerIndex = i + 1;
            }
        }
        if (voteCount[winnerIndex] >= (totalNumberOfVotes / 2)) {
            return election.candidateList[winnerIndex].getName();
        }
        return null; //no winner
    }
}
