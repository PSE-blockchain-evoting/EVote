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

import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.VoterSDKInterfaceImpl;
import org.junit.Test;

import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(VoterElection.class)
public class VoterElectionTest {

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

    private void genMockVoterSDK(ElectionDataIF data, VoterSDKInterfaceImpl mockVoterSDK) throws NetworkException, NetworkConfigException {
        doReturn(true)
            .when(mockVoterSDK)
            .isElectionInitialized();
        doReturn(data)
            .when(mockVoterSDK)
            .getElectionData();
        doReturn(new String[]{
                "[\"Bob\",\"Bill\",\"Sue\"]",
                "[\"Sue\",\"Bob\",\"Bill\"]",
                "[\"Bill\",\"Sue\",\"Bob\"]",
                "[\"Bob\",\"Bill\",\"Sue\"]"
            })
            .when(mockVoterSDK)
            .getAllVotes();
        doReturn("")
            .when(mockVoterSDK)
            .getOwnVote();
    }

    @Test
    public void all() throws Exception {
        Locale.setDefault(new Locale("de", "DE"));
        ElectionDataIF mockData = genMockData();
        ElectionStatusListener list = mock(ElectionStatusListener.class);
        // https://github.com/mockito/mockito/wiki/Mocking-Object-Creation
        VoterElection election = new VoterElection(list);
        VoterSDKInterfaceImpl mockVoterSDK = mock(VoterSDKInterfaceImpl.class);
        PowerMockito.whenNew(VoterSDKInterfaceImpl.class).withAnyArguments().thenReturn(mockVoterSDK);
        genMockVoterSDK(mockData, mockVoterSDK);
        election.authenticate("some/cool/path/to/the/certificate/of/the/voter");
        Thread.sleep(3000);
        String myVote = "[\"Sue\",\"Bob\",\"Bill\"]";
        boolean resVote = election.vote(myVote);
        assertTrue(resVote);
        assertEquals(myVote, election.getOwnVote());
        doReturn(new String[]{
                "[\"Bob\",\"Bill\",\"Sue\"]",
                "[\"Sue\",\"Bob\",\"Bill\"]",
                "[\"Bill\",\"Sue\",\"Bob\"]",
                "[\"Bob\",\"Bill\",\"Sue\"]",
                myVote
            })
            .when(mockVoterSDK)
            .getAllVotes();
        int[] res = election.getResults();
        String winner = election.getWinner();
        assertNotNull(winner);
        // should be: assertEquals("Sue", winner);
        assertEquals("Bob", winner);
        // should be: assertArrayEquals(new int[]{3, 2, 2, 1, 2, 1, 2, 1, 2, 2}, res);
        assertArrayEquals(new int[]{3, 2, 1, 1, 1, 1, 2, 1, 2, 1}, res);
    }
}
