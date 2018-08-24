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


import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionData;
import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import javax.json.Json;
import javax.json.JsonReader;

import org.hyperledger.fabric.sdk.BlockEvent;
import org.hyperledger.fabric.sdk.ChaincodeResponse;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.QueryByChaincodeRequest;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.HFCAEnrollment;
import org.hyperledger.fabric_ca.sdk.HFCAIdentity;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HFCAClient.class, HFClient.class, ElectionData.class})
public class VoterSDKInterfaceImplTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private VoterSDKInterfaceImpl objUnderTest;
    private TransactionProposalRequest request;
    private Channel channel;
    private ArrayList<ProposalResponse> responses;

    @Before
    public void setup() throws EnrollmentException, InvalidArgumentException, IOException, NetworkException,
            AuthenticationException, InternalSDKException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        PowerMockito.mockStatic(HFClient.class);

        HFClient hfClient = mock(HFClient.class);
        this.channel = mock(Channel.class);
        this.request = TransactionProposalRequest.newInstance(null);
        QueryByChaincodeRequest queryRequest = QueryByChaincodeRequest.newInstance(null);

        BlockEvent.TransactionEvent event = mock(BlockEvent.TransactionEvent.class);
        CompletableFuture<BlockEvent.TransactionEvent> future = new CompletableFuture<>();
        future.complete(event);

        ProposalResponse response = mock(ProposalResponse.class);
        when(response.getStatus()).thenReturn(ChaincodeResponse.Status.SUCCESS);
        when(response.isVerified()).thenReturn(true);
        this.responses = new ArrayList<>();
        responses.add(response);

        when(hfClient.getChannel(anyString())).thenReturn(channel);
        when(hfClient.newChannel(anyString())).thenReturn(channel);
        when(hfClient.newTransactionProposalRequest()).thenReturn(request);
        when(hfClient.newQueryProposalRequest()).thenReturn(queryRequest);
        when(HFClient.createNewInstance()).thenReturn(hfClient);
        when(channel.sendTransaction(anyCollection())).thenReturn(future);
        when(channel.queryByChaincode(any())).thenReturn(responses);

        File appUserFile = tempFolder.newFile("appuser");
        AppUser appUser = new AppUser("", "", null, "", "", null);
        FileOutputStream fos = new FileOutputStream(appUserFile);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(appUser);
        SDKEventListener listener = mock(SDKEventListener.class);
        this.objUnderTest = new VoterSDKInterfaceImpl(appUserFile.getPath(), listener);
    }

    @Test
    public void voteTest() throws NetworkException, NetworkConfigException {
        String vote = "{ \"candidate\": \"candidate1\"}";
        this.objUnderTest.vote(vote);
        assertEquals(1, this.request.getArgs().size());
        assertEquals(vote, this.request.getArgs().get(0));
        assertEquals("voteInvokation", this.request.getFcn());
    }

    @Test(expected = NetworkException.class)
    public void voteProposalExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.sendTransactionProposal(any())).thenThrow(ProposalException.class);
        String vote = "{ \"candidate\": \"candidate1\"}";
        this.objUnderTest.vote(vote);
    }

    @Test(expected = NetworkConfigException.class)
    public void voteInvalidArgumentExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.sendTransactionProposal(any()))
                .thenThrow(org.hyperledger.fabric.sdk.exception.InvalidArgumentException.class);
        String vote = "{ \"candidate\": \"candidate1\"}";
        this.objUnderTest.vote(vote);
    }

    @Test
    public void getOwnVoteTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException {
        String vote = "{ \"candidate\": \"candidate1\"}";
        when(this.responses.get(0).getChaincodeActionResponsePayload()).thenReturn(vote.getBytes());
        String res = this.objUnderTest.getOwnVote();
        assertEquals(vote, res);
    }

    @Test(expected = NetworkException.class)
    public void getOwnVoteProposalExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any())).thenThrow(ProposalException.class);
        this.objUnderTest.getOwnVote();
    }

    @Test(expected = NetworkConfigException.class)
    public void getOwnVoteInvalidArgumentExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any()))
                .thenThrow(org.hyperledger.fabric.sdk.exception.InvalidArgumentException.class);
        this.objUnderTest.getOwnVote();
    }

}
