package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;

import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.EnrollmentException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.SupervisorSDKInterface;

import java.io.IOException;
import java.util.ResourceBundle;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ResourceBundle.class)
@Ignore
public class SDKConnectionITTest {

    private SupervisorSDKInterface sdkInterface;
    private SDKEventListener eventListener;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @BeforeClass
    public void setup() throws IOException, NetworkException, AuthenticationException, InternalSDKException, NetworkConfigException {
        eventListener = mock(SDKEventListener.class);
        ResourceBundle testBundle = ResourceBundle.getBundle("testConfig");
        PowerMockito.mockStatic(ResourceBundle.class);
        when(ResourceBundle.getBundle("config")).thenReturn(testBundle);
        sdkInterface = SupervisorSDKInterfaceImpl.createInstance("admin", "adminpw", tempFolder.newFile("adminIdent").getAbsolutePath(), eventListener);
    }

    @Test
    public void createUserTest() throws IOException, EnrollmentException {
        sdkInterface.createUser("testuser1", tempFolder.newFile("user1Ident").getAbsolutePath());
    }

}
