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
        Candidate candBob = mock(Candidate.class);
        Candidate candSue = mock(Candidate.class);
        Candidate candBill = mock(Candidate.class);
        when(candBob.getName()).thenReturn("Bob");
        when(candBob.getDescription()).thenReturn("Mega cool candidate #1");
        when(candSue.getName()).thenReturn("Sue");
        when(candSue.getDescription()).thenReturn("Mega cool candidate #2");
        when(candBill.getName()).thenReturn("Bill");
        when(candBill.getDescription()).thenReturn("Mega cool candidate #3");
        String[] candidates = new String[]{candBob.getName(), candSue.getName(), candBill.getName()};
        String[] candidateDescriptions = new String[]{candBob.getDescription(), candSue.getDescription(),
            candBill.getDescription()};

        // Prepare election and election data.
        ElectionDataIF electionData = mock(ElectionDataIF.class);
        when(electionData.getCandidates()).thenReturn(candidates);
        when(electionData.getCandidateDescriptions()).thenReturn(candidateDescriptions);
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
        //assertEquals("Mega cool candidate #2", cand.getDescription()); we changed determineWinner return type to String

        // Verify determineResults is right
        int[] results = irv.determineResults();
        assertArrayEquals(new int[]{3, 2, 2, 1, 2, 1, 2, 1, 2, 2}, results);
    }
}
