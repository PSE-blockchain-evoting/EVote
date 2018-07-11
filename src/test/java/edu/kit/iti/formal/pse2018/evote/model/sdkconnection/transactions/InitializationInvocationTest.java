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
