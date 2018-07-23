package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

public class RelativeMajorityVotingSystem extends MajorityVotingSystem {

    public RelativeMajorityVotingSystem(Election election) {
        super(election);
    }

    @Override
    String determineWinner() {
        int max = -1;
        int index = -1;
        for (int i = 0; i < votes.length; i++) {
            if (max < votes[i]) {
                max = votes[i];
                index = i;
            }
        }
        return election.electionDataIF.getCandidates()[index];
    }
}
