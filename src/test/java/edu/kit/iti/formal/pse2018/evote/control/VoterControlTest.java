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

package edu.kit.iti.formal.pse2018.evote.control;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.model.VoterControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;
import edu.kit.iti.formal.pse2018.evote.view.VoterControlToViewIF;
import edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol.SupervisorControl;
import edu.kit.iti.formal.pse2018.evote.control.votercontrol.VoterControl;

import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class VoterControlTest {
	@Test
	public void auth() throws NetworkException, AuthenticationException, InternalSDKException, NetworkConfigException {
		Locale.setDefault(new Locale("de", "DE"));
		VoterControlToViewIF view = mock(VoterControlToViewIF.class);
		VoterControlToModelIF model = mock(VoterControlToModelIF.class);
        VoterControl control = new VoterControl(view, model);
        when(view.getVote()).thenReturn("Bill");
        control.getVotedListener().actionPerformed(null);
        verify(view, times(1)).showSuccess(anyString());
        verify(view, times(1)).showWait();
        when(model.vote(anyString())).thenThrow(new NetworkException("Something"));
        control.getVotedListener().actionPerformed(null);
        verify(view, times(1)).showError(anyString());

        //control.getAuthenticationListener().actionPerformed(null);

	}
}
