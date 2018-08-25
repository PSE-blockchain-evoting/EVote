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


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.HFCAIdentity;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HFCAClient.class,HFClient.class})
public class SupervisorSDKInterfaceImplBaseTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private SupervisorSDKInterfaceImpl objUnderTest;

    @Test
    public void createInstance() throws EnrollmentException, InvalidArgumentException, IOException, NetworkException,
            AuthenticationException, InternalSDKException, NetworkConfigException, ClassNotFoundException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException {
        PowerMockito.mockStatic(HFCAClient.class);
        PowerMockito.mockStatic(HFClient.class);

        HFCAClient hfcaClient = mock(HFCAClient.class);
        Enrollment enrollment = mock(Enrollment.class, withSettings().serializable());
        HFClient hfClient = mock(HFClient.class);
        Channel channel = mock(Channel.class);
        HFCAIdentity identity = mock(HFCAIdentity.class);

        when(hfcaClient.enroll(anyString(), anyString())).thenReturn(enrollment);
        when(hfcaClient.newHFCAIdentity(anyString())).thenReturn(identity);
        when(HFCAClient.createNewInstance(anyString(), eq(null))).thenReturn(hfcaClient);
        when(hfClient.newChannel(anyString())).thenReturn(channel);
        when(hfClient.getChannel(anyString())).thenReturn(channel);
        when(HFClient.createNewInstance()).thenReturn(hfClient);

        File appUserFile = tempFolder.newFile("appuser");
        SupervisorSDKInterfaceImpl supervisorSDKInterface = SupervisorSDKInterfaceImpl.createInstance("admin",
                "password", appUserFile.getPath());
        FileInputStream fis = new FileInputStream(appUserFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object writtenObject = ois.readObject();
        assertTrue(writtenObject instanceof AppUser);
    }

    @Test
    public void createInstanceFromFile() throws EnrollmentException, InvalidArgumentException, IOException,
            NetworkException, AuthenticationException, InternalSDKException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException {
        PowerMockito.mockStatic(HFCAClient.class);
        PowerMockito.mockStatic(HFClient.class);

        HFCAClient hfcaClient = mock(HFCAClient.class);
        Enrollment enrollment = mock(Enrollment.class, withSettings().serializable());
        HFClient hfClient = mock(HFClient.class);
        Channel channel = mock(Channel.class);

        when(hfcaClient.enroll(anyString(), anyString())).thenReturn(enrollment);
        when(HFCAClient.createNewInstance(anyString(), eq(null))).thenReturn(hfcaClient);
        when(hfClient.newChannel(anyString())).thenReturn(channel);
        when(hfClient.getChannel(anyString())).thenReturn(channel);
        when(HFClient.createNewInstance()).thenReturn(hfClient);

        File appUserFile = tempFolder.newFile("appuser");
        AppUser appUser = new AppUser("", "", null, "", "", null);
        FileOutputStream fos = new FileOutputStream(appUserFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(appUser);
        SupervisorSDKInterfaceImpl supervisorSDKInterface = SupervisorSDKInterfaceImpl.createInstance(
                appUserFile.getPath());
    }


}
