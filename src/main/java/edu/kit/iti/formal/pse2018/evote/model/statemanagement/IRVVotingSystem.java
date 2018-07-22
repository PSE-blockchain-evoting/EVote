package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import edu.kit.iti.formal.pse2018.evote.exceptions.LoadVoteException;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;


public class IRVVotingSystem extends VotingSystem {
    /**
    * All Candidate Names.
    */
    private String[] candidates;

    /**
     * All Candidate Descriptions.
     */
    private String[] candidateDescriptions;

    /**
    * Scores of each candidate at some round.
    */
    private int[] scores;

    /**
    * Who is still in competition.
    */
    private boolean[] stillIn;

    /**
    * votes[i] is the vote with preferences of voter #i.
    */


    /**
     * create instance of IRVVotingSystem.
     * @param election Election with participating Candidates
     */
    public IRVVotingSystem(Election election) {
        super(election);
    }

    @Override
    public void loadVote(String vote) throws LoadVoteException, WrongCandidateNameException {

    }

    @Override
    public String determineWinner() {
        return null;
    }

    /**
     * Returns scores of each candidate after the last round.
     */
    @Override
    public int[] determineResults() {
        return null;
    }
}
