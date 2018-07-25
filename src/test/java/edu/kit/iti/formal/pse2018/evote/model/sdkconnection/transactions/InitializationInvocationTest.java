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

import edu.kit.iti.formal.pse2018.evote.model.ElectionData;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionEndCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VoterPercentileCondition;
import edu.kit.iti.formal.pse2018.evote.utils.VotingSystemType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.hyperledger.fabric.sdk.HFClient;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class InitializationInvocationTest {

    @Test
    public void all() throws IOException {
        HFClient client = mock(HFClient.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2006, Calendar.JANUARY, 2, 15, 4, 5);
        Date startDate = calendar.getTime();
        calendar.set(2006, Calendar.JANUARY, 4, 15, 4, 5);
        Date endDate = calendar.getTime();
        ElectionEndCondition condition = new VoterPercentileCondition(60);
        ElectionData electionData = new ElectionData("CooleWahl", "Diese Wahl is sehr cool",
                VotingSystemType.ABSOLUTEMAJORITY, new String[]{"hans", "peter", "werner"},
                new String[]{"cooler dude", "nicer mensch", "sicker kerl"}, startDate, endDate, condition, 3);
        InitializationInvocation init = new InitializationInvocation(client, electionData);
        String[] results = init.buildArgumentStrings();
        assertEquals(1, results.length);
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/electionDataExample.json")));
        assertEquals(json.replaceAll(" ", "").replaceAll("\n", ""),
                results[0].replaceAll(" ", "").replaceAll("\n", ""));
    }
}
