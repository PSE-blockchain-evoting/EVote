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

package edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions;

import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionEndCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VoterPercentileCondition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hyperledger.fabric.sdk.HFClient;
import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ElectionDataQueryTest {

    @Test
    public void all() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/electionDataExample.json")));
        HFClient client = mock(HFClient.class);
        ElectionDataQuery query = new ElectionDataQuery(client);
        query.parseResultString(json);
        ElectionDataIF result = query.getResult();
        assertEquals("CooleWahl", result.getName());
        assertEquals("Diese Wahl is sehr cool", result.getDescription());
        assertEquals("ABSOLUTEMAJORITY", result.getVotingSystem().name());
        assertArrayEquals(new String[]{"hans", "peter", "werner"}, result.getCandidates());
        assertArrayEquals(new String[]{"cooler dude", "nicer mensch", "sicker kerl"}, result.getCandidateDescriptions());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2006, Calendar.JANUARY, 2, 15, 4, 5);
        //assertEquals(calendar.getTime().toString(), result.getStartDate().toString());
        calendar.set(2006, Calendar.JANUARY, 4, 15, 4, 5);
        //assertEquals(calendar.getTime().toString(), result.getEndDate().toString());
        ElectionEndCondition condition = new VoterPercentileCondition(60);
        //assertEquals(condition.asJsonObject().toString(), result.getEndCondition().asJsonObject().toString());
    }
}
