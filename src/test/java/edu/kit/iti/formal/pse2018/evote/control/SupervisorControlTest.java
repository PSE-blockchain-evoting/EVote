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
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;
import edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol.SupervisorControl;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SupervisorControlTest {
	private SupervisorControlToModelIF model;
	private SupervisorControlToViewIF view;

	@Before
	public void tor() {
		Locale.setDefault(new Locale("de", "DE"));
		this.model = mock(SupervisorControlToModelIF.class);
		this.view = mock(SupervisorControlToViewIF.class);
	}

	@Test
	public void auth() throws NetworkException, AuthenticationException, InternalSDKException, NetworkConfigException {
        String login = "nick";
        String pass = "p@ss";
        when(view.getUsername()).thenReturn(login);
        when(view.getPassword()).thenReturn(pass);
		SupervisorControl control = new SupervisorControl(view, model);
		when(model.isElectionInitialized()).thenReturn(false);
		ActionListener authLi = control.getFirstAuthenticationListener();
		assertTrue(authLi != null);
		authLi.actionPerformed(null);
        verify(view, times(1)).showFrontpage();
		verify(model, times(1)).firstAuthentication(login, pass);
		when(model.isElectionInitialized()).thenReturn(true);
		authLi.actionPerformed(null);
		verify(view, times(1)).showResults();
		when(model.firstAuthentication(anyString(), anyString())).thenThrow(NetworkException.class);
		authLi.actionPerformed(null);
		verify(view, times(1)).showError(anyString());

		ActionListener l2 = control.getImportConfigListener();
		assertTrue(l2 != null);

		ActionListener l1 = control.getFinishElectionListener();
		assertTrue(l1 != null);
		ActionListener l3 = control.getExportConfigListener();
		assertTrue(l3 != null);
		ActionListener l5 = control.getConfirmedConfigListener();
		assertTrue(l5 != null);
		ActionListener l6 = control.getNewConfigListener();
		assertTrue(l6 != null);
		ActionListener l7 = control.getStartElectionListener();
		assertTrue(l7 != null);
		ActionListener l8 = control.getAuthenticationListener();
		assertTrue(l8 != null);
		ActionListener l9 = control.getLogoutListener();
		assertTrue(l9 != null);
	}

	@Test
    public void imp() throws IOException {
		String path = "path/somepath";
		when(view.getImportPath()).thenReturn(path);
		SupervisorControl cont = new SupervisorControl(view, model);
		ActionListener imp = cont.getImportConfigListener();
		imp.actionPerformed(null);
		verify(model, times(1)).importConfig(path);
		doThrow(IOException.class)
				.when(model)
				.importConfig(anyString());
	}
}
