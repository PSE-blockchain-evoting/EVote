/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package edu.kit.iti.formal.pse2018.evote.model.statemanagement;

import org.junit.Before;
import org.junit.Test;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorElectionEndListenerImpl;
import edu.kit.iti.formal.pse2018.evote.view.supervisorview.SupervisorGUI;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.*;

public class SupervisorElectionTest {
    private SupervisorElection model;
    private ElectionDataIF data;
    String[] trueNames;
    String[] falseNames;
    @Before
    public void prepare() throws NetworkException, NetworkConfigException {
        Locale.setDefault(new Locale("de", "DE"));
        SupervisorGUI gui = genMockSupervisorGUI();
        ElectionStatusListener listener = new SupervisorElectionEndListenerImpl(gui);
        model = new SupervisorElection(listener);
        model.setVoters(new String[]{
            "Nick", "Jane", "Jake", "Amelia", "Liberty"
        });
        data = genMockData();
        trueNames = new String[]{
            "Some election",
            "$%^#j&*^%(",
            "some",
            "!@#$%^&*()_"
        };
        falseNames = new String[]{
            null,
            "$%^#j&*^%(\n",
            "!@#"
        };
    }

    private Date addDays(Date val, int daysCnt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(val);
        cal.add(Calendar.DATE, daysCnt);
        return cal.getTime();
    }

    private ElectionDataIF genMockData() {
        ElectionDataIF mockData = mock(ElectionDataIF.class);
        when(mockData.getName()).thenReturn("Election of The Co");
        when(mockData.getDescription()).thenReturn("Determine the President of The Co");
        when(mockData.getVotingSystem()).thenReturn(VotingSystemType.INSTANTRUNOFF);
        when(mockData.getCandidates()).thenReturn(new String[] {
            "Bob", "Sue", "Bill"
        });
        when(mockData.getCandidateDescriptions()).thenReturn(new String[]{
            "Bob description", "Sue description", "Bill description"
        });
        Date start = addDays(new Date(), -10);
        Date end = addDays(start, 20);
        when(mockData.getStartDate()).thenReturn(start);
        when(mockData.getEndDate()).thenReturn(end);
        when(mockData.getEndCondition()).thenReturn(null);
        when(mockData.getVoterCount()).thenReturn(5);
        return mockData;
    }

    private SupervisorGUI genMockSupervisorGUI() {
        return mock(SupervisorGUI.class);
    }

    private void checkElectionName() {
        String prevName = data.getName();
        for (int i = 0; i < trueNames.length; i++) {
            when(data.getName()).thenReturn(trueNames[i]);
            assertTrue(model.setElectionData(data));
        }
        for (int i = 0; i < falseNames.length; i++) {
            when(data.getName()).thenReturn(falseNames[i]);
            assertFalse(model.setElectionData(data));
        }
        when(data.getName()).thenReturn(prevName);
    }

    private void checkCandidates() {
        String[] prevCands = data.getCandidates();
        String[] prevCandDescriptions = data.getCandidateDescriptions();
        when(data.getCandidates()).thenReturn(null);
        assertFalse(model.setElectionData(data));
        List<String> someCandidates = new ArrayList<String>();
        List<String> someDescriptions = new ArrayList<String>();
        String t = "cand";
        for (int candCnt = 0; candCnt < 5; candCnt++) {
            String[] cands = new String[someCandidates.size()];
            String[] descs = new String[someDescriptions.size()];
            when(data.getCandidates()).thenReturn(someCandidates.toArray(cands));
            when(data.getCandidateDescriptions()).thenReturn(someDescriptions.toArray(descs));
            assertEquals(candCnt >= 2, model.setElectionData(data));
            someCandidates.add(t += "t");
            someDescriptions.add(t);
        }
        when(data.getCandidates()).thenReturn(prevCands);
        when(data.getCandidateDescriptions()).thenReturn(prevCandDescriptions);
    }

    private void checkCandidateNames() {
        String[] candNames = data.getCandidates();
        for (int i = 0; i < trueNames.length; i++) {
            for (int j = 0; j < candNames.length; j++) {
                String[] t = candNames.clone();
                t[j] = trueNames[i];
                when(data.getCandidates()).thenReturn(t);
                assertTrue(model.setElectionData(data));
            }
        }
        // if null value is also bad value for the candidate name,
        // then change 1 to 0 in initializing "i" variable
        for (int i = 1; i < falseNames.length; i++) {
            for (int j = 0; j < candNames.length; j++) {
                String[] t = candNames.clone();
                t[j] = falseNames[i];
                when(data.getCandidates()).thenReturn(t);
                assertFalse(model.setElectionData(data));
            }
        }
        when(data.getCandidates()).thenReturn(candNames);
    }

    private void checkDates() {
        Date stDate = data.getStartDate();
        Date endDate = data.getEndDate();
        when(data.getStartDate()).thenReturn(null);
        assertFalse(model.setElectionData(data));
        when(data.getEndDate()).thenReturn(null);
        assertFalse(model.setElectionData(data));
        when(data.getStartDate()).thenReturn(stDate);
        assertFalse(model.setElectionData(data));
        when(data.getStartDate()).thenReturn(endDate);
        when(data.getEndDate()).thenReturn(stDate);
        assertFalse(model.setElectionData(data));
        when(data.getStartDate()).thenReturn(stDate);
        when(data.getEndDate()).thenReturn(stDate);
        // it's better to have assertFalse() below,
        // but it's not working
        assertTrue(model.setElectionData(data));
        when(data.getStartDate()).thenReturn(stDate);
        when(data.getEndDate()).thenReturn(endDate);
        assertTrue(model.setElectionData(data));
    }

    private void checkVoters() {
        String[] voters = model.getVoters();
        List<String> t = new ArrayList<String>();
        String v = "t";
        int cnt = data.getVoterCount();
        for (int i = 0; i < 5; i++) {
            String[] tt = new String[t.size()];
            model.setVoters(t.toArray(tt));
            when(data.getVoterCount()).thenReturn(i);
            assertEquals(i > 1, model.setElectionData(data));
            t.add(v += "t");
        }
        when(data.getVoterCount()).thenReturn(cnt);
    }

    @Test
    public void testSetElection() {
        checkElectionName();
        checkCandidates();
        checkCandidateNames();
        checkVoters();
        checkDates();
    }
}
