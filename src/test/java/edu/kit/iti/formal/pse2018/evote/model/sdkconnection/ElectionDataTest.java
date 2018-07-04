package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionEndCondition;
import edu.kit.iti.formal.pse2018.evote.utils.TimeOnlyCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

import java.util.Calendar;
import java.util.Date;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ElectionDataTest {

    @Test
    public void all() {
        String name = "TestName";
        String description = "TestDescription";
        VotingSystemType type = VotingSystemType.ABSOLUTEMAJORITY;
        String[] candidateNames = new String[]{"TestCName1", "TestCName2"};
        String[] candidateDesc = new String[]{"TestCDesc1", "TestCDesc2"};
        Calendar c = Calendar.getInstance();
        Date startDate = c.getTime();
        c.add(Calendar.DATE, 1);
        Date endDate = c.getTime();
        ElectionEndCondition cond = mock(TimeOnlyCondition.class);
        int voterCount = 10;
        ElectionData data = new ElectionData(name, description, type, candidateNames, candidateDesc, startDate,
                endDate, cond, voterCount);
        assertEquals(name, data.getName());
        assertEquals(description, data.getDescription());
        assertEquals(type, data.getVotingSystem());
        assertArrayEquals(candidateNames, data.getCandidates());
        assertArrayEquals(candidateDesc, data.getCandidateDescriptions());
        assertEquals(startDate, data.getStartDate());
        assertEquals(endDate, data.getEndDate());
        assertEquals(voterCount, data.getVoterCount());
        assertEquals(cond, data.getEndCondition());
    }
}
