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

package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.model.ElectionData;
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
