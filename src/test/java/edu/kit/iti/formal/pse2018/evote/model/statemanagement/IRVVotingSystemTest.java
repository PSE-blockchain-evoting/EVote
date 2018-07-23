package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import org.junit.Test;
import edu.kit.iti.formal.pse2018.evote.exceptions.WrongCandidateNameException;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import static org.mockito.Mockito.*;

import java.util.Locale;

import static org.junit.Assert.*;

public class IRVVotingSystemTest {

    @Test
    public void all() throws WrongCandidateNameException {
        // Test IRVVotingSystem class by example from wikipedia.
        // https://en.wikipedia.org/wiki/Instant-runoff_voting
        Locale.setDefault(new Locale("de", "DE"));

        // First: prepare candidates.
        String[] candidates = new String[]{"Bob", "Sue", "Bill"};

        // Prepare election and election data.
        ElectionDataIF electionData = mock(ElectionDataIF.class);
        when(electionData.getCandidates()).thenReturn(candidates);
        Election election = mock(Election.class);
        when(election.getElectionData()).thenReturn(electionData);

        // Load votes
        IRVVotingSystem irv = new IRVVotingSystem(election);
        // voter "a" -> Bob rank1, Sue rank3, Bill rank2
        irv.loadVote("[\"Bob\",\"Bill\",\"Sue\"]");
        // voter "b" -> Bob rank2, Sue rank1, Bill rank3
        irv.loadVote("[\"Sue\",\"Bob\",\"Bill\"]");
        // voter "c" -> Bob rank3, Sue rank2, Bill rank1
        irv.loadVote("[\"Bill\",\"Sue\",\"Bob\"]");
        // voter "d" -> Bob rank1, Sue rank3, Bill rank2
        irv.loadVote("[\"Bob\",\"Bill\",\"Sue\"]");
        // voter "e" -> Bob rank2, Sue rank1, Bill rank3
        irv.loadVote("[\"Sue\",\"Bob\",\"Bill\"]");

        // Verify determineWinner is right
        String cand = irv.determineWinner();

        assertNotNull(cand);
        assertEquals("Sue", cand);

        // Verify determineResults is right
        int[] results = irv.determineResults();
        assertArrayEquals(new int[]{3, 2, 2, 1, 2, 1, 2, 1, 2, 2}, results);
    }
}
