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

import edu.kit.iti.formal.pse2018.evote.exceptions.*;
import edu.kit.iti.formal.pse2018.evote.model.ElectionStatusListener;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.VoterSDKInterfaceImpl;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VoterElectionTest {

    @Before
    public void setup() throws Exception {
        VoterSDKInterfaceImpl voterSDKInterface = mock(VoterSDKInterfaceImpl.class);
        PowerMockito.whenNew(VoterSDKInterfaceImpl.class).withAnyArguments().thenReturn(voterSDKInterface);
        when(voterSDKInterface.isElectionInitialized()).thenReturn(true);
        when(voterSDKInterface.getOwnVote()).thenReturn("testVote");
    }
    @Test
    public void all() throws NetworkException, NetworkConfigException, ElectionRunningException, AuthenticationException, InternalSDKException, WrongCandidateNameException {
        Locale.setDefault(new Locale("de", "DE"));
        ElectionStatusListener electionStatusListener = mock(ElectionStatusListener.class);
        VoterElection voterElectionTest = new VoterElection(electionStatusListener);

        voterElectionTest.authenticate("testPath");

        //assert (voterElectionTest.hasVoted());
        //assert (!voterElectionTest.sdkEventListenerImpl.hasEnded); this might not work as expected
    }
}
