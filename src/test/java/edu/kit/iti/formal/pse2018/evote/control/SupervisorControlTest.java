package edu.kit.iti.formal.pse2018.evote.control;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorControlToModelIF;
import edu.kit.iti.formal.pse2018.evote.view.SupervisorControlToViewIF;
import edu.kit.iti.formal.pse2018.evote.control.supervisorcontrol.SupervisorControl;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SupervisorControlTest {
	@Test
	public void auth() throws NetworkException, AuthenticationException, InternalSDKException, NetworkConfigException {
		Locale.setDefault(new Locale("de", "DE"));
        String login = "nick";
        String pass = "p@ss";
		SupervisorControlToViewIF view = mock(SupervisorControlToViewIF.class);
		SupervisorControlToModelIF model = mock(SupervisorControlToModelIF.class);
        when(view.getUsername()).thenReturn(login);
        when(view.getPassword()).thenReturn(pass);
		SupervisorControl control = new SupervisorControl(view, model);
		control.getFirstAuthenticationListener().actionPerformed(null);
        verify(view, times(1)).showFrontpage();
        verify(model, times(1)).firstAuthentication(login, pass);
	}
}
