package edu.kit.iti.formal.pse2018.evote.model.sdkconnection;


import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;

import java.io.*;

import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HFCAClient.class,HFClient.class})
public class SupervisorSDKInterfaceImplMethodTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private SupervisorSDKInterfaceImpl objUnderTest;

    @Before
    public void setup() throws IOException, ClassNotFoundException, EnrollmentException, InvalidArgumentException {
        PowerMockito.mockStatic(HFCAClient.class);
        PowerMockito.mockStatic(HFClient.class);

        HFCAClient hfcaClient = mock(HFCAClient.class);
        Enrollment enrollment = mock(Enrollment.class, withSettings().serializable());
        HFClient hfClient = mock(HFClient.class);
        Channel channel = mock(Channel.class);

        when(hfcaClient.enroll(anyString(), anyString())).thenReturn(enrollment);
        when(HFCAClient.createNewInstance(anyString(), eq(null))).thenReturn(hfcaClient);
        when(hfClient.getChannel(anyString())).thenReturn(channel);
        when(HFClient.createNewInstance()).thenReturn(hfClient);

        File appUserFile = tempFolder.newFile("appuser");
        AppUser appUser = new AppUser("", "", null, "", "", null);
        FileOutputStream fos = new FileOutputStream(appUserFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(appUser);
        SDKEventListener listener = mock(SDKEventListener.class);
        this.objUnderTest = SupervisorSDKInterfaceImpl.createInstance(
                appUserFile.getPath(), listener);
    }

    //TODO: Add further tests for different methods in SupervisorSDKInterfaceImpl

    @Test

    public void dummy() {
        assert true;
    }


}
