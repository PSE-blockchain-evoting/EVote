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


import org.junit.Before;
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
import edu.kit.iti.formal.pse2018.evote.model.ElectionData;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javax.json.Json;
import javax.json.JsonReader;

import org.hyperledger.fabric.sdk.BlockEvent;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.HFCAEnrollment;
import org.hyperledger.fabric_ca.sdk.HFCAIdentity;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HFCAClient.class, HFClient.class, ElectionData.class})
public class SupervisorSDKInterfaceImplMethodTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private SupervisorSDKInterfaceImpl objUnderTest;
    private Channel channel;

    @Before
    public void setup() throws EnrollmentException, InvalidArgumentException, IOException, NetworkException,
            AuthenticationException, InternalSDKException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException {
        PowerMockito.mockStatic(HFCAClient.class);
        PowerMockito.mockStatic(HFClient.class);

        HFCAClient hfcaClient = mock(HFCAClient.class);
        HFCAIdentity identity = mock(HFCAIdentity.class);
        HFCAEnrollment enrollment = mock(HFCAEnrollment.class, withSettings().serializable());
        HFClient hfClient = mock(HFClient.class);
        this.channel = mock(Channel.class);
        TransactionProposalRequest request = mock(TransactionProposalRequest.class);
        BlockEvent.TransactionEvent event = mock(BlockEvent.TransactionEvent.class);
        CompletableFuture<BlockEvent.TransactionEvent> future = new CompletableFuture<>();
        future.complete(event);

        when(hfcaClient.enroll(any(), any())).thenReturn(enrollment);
        when(hfcaClient.newHFCAIdentity(anyString())).thenReturn(identity);
        when(HFCAClient.createNewInstance(anyString(), eq(null))).thenReturn(hfcaClient);
        when(hfClient.getChannel(anyString())).thenReturn(channel);
        when(hfClient.newChannel(anyString())).thenReturn(channel);
        when(hfClient.newTransactionProposalRequest()).thenReturn(request);
        when(HFClient.createNewInstance()).thenReturn(hfClient);
        when(channel.sendTransaction(anyCollection())).thenReturn(future);

        File appUserFile = tempFolder.newFile("appuser");
        AppUser appUser = new AppUser("", "", null, "", "", null);
        FileOutputStream fos = new FileOutputStream(appUserFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(appUser);
        this.objUnderTest = SupervisorSDKInterfaceImpl.createInstance(
                appUserFile.getPath());
    }

    //TODO: Add further tests for different methods in SupervisorSDKInterfaceImpl

    @Test
    public void destroyElectionTest() throws NetworkException, NetworkConfigException {
        this.objUnderTest.destroyElection();
    }

    @Test(expected = NetworkException.class)
    public void destroyElectionProposalExceptionTest() throws org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException, NetworkException, NetworkConfigException {
        when(this.channel.sendTransactionProposal(any())).thenThrow(ProposalException.class);
        this.objUnderTest.destroyElection();
    }

    @Test(expected = NetworkConfigException.class)
    public void destroyElectionInvalidArgumentExceptionTest() throws org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException, NetworkException, NetworkConfigException {
        when(this.channel.sendTransactionProposal(any()))
                .thenThrow(org.hyperledger.fabric.sdk.exception.InvalidArgumentException.class);
        this.objUnderTest.destroyElection();
    }

    @Test
    public void createElectionTest() throws NetworkException, NetworkConfigException, FileNotFoundException {
        JsonReader reader = Json.createReader(new FileInputStream("src/test/resources/electionDataExample.json"));
        ElectionDataIF electionData = ElectionData.fromJSon(reader.readObject());
        this.objUnderTest.createElection(electionData);
    }

    @Test(expected = NetworkException.class)
    public void createElectionProposalExceptionTest() throws NetworkException, NetworkConfigException, FileNotFoundException, org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.sendTransactionProposal(any()))
                .thenThrow(ProposalException.class);
        JsonReader reader = Json.createReader(new FileInputStream("src/test/resources/electionDataExample.json"));
        ElectionDataIF electionData = ElectionData.fromJSon(reader.readObject());
        this.objUnderTest.createElection(electionData);
    }

    @Test(expected = NetworkConfigException.class)
    public void createElectionInvalidArgumentExceptionTest() throws NetworkException, NetworkConfigException, FileNotFoundException, org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.sendTransactionProposal(any()))
                .thenThrow(org.hyperledger.fabric.sdk.exception.InvalidArgumentException.class);
        JsonReader reader = Json.createReader(new FileInputStream("src/test/resources/electionDataExample.json"));
        ElectionDataIF electionData = ElectionData.fromJSon(reader.readObject());
        this.objUnderTest.createElection(electionData);
    }

    @Test
    public void createUserTest() throws IOException, edu.kit.iti.formal.pse2018.evote.exceptions.EnrollmentException, ClassNotFoundException {
        String name = "TestUser";
        String filePath = tempFolder.newFile("testUser").getAbsolutePath();
        this.objUnderTest.createUser(name, filePath);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        Object readObject = ois.readObject();
        assertTrue(readObject instanceof AppUser);
        AppUser readUser = (AppUser) readObject;
        assertEquals(name, readUser.getName());
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        assertEquals(bundle.getString("affiliation"), readUser.getAffiliation());
        assertEquals(bundle.getString("mspID"), readUser.getMspId());
    }

}
