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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.hyperledger.fabric.sdk.BlockEvent;
import org.hyperledger.fabric.sdk.ChaincodeResponse;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.QueryByChaincodeRequest;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HFCAClient.class, HFClient.class, ElectionData.class})
public class SDKInterfaceImplTest {

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private SDKInterfaceImpl objUnderTest;
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
        when(response.getChaincodeActionResponsePayload()).thenReturn(new byte[]{});
        this.responses = new ArrayList<>();
        responses.add(response);

        when(hfClient.getChannel(anyString())).thenReturn(channel);
        when(hfClient.newChannel(anyString())).thenReturn(channel);
        when(hfClient.newTransactionProposalRequest()).thenReturn(request);
        when(hfClient.newQueryProposalRequest()).thenReturn(queryRequest);
        when(HFClient.createNewInstance()).thenReturn(hfClient);
        when(channel.sendTransaction(anyCollection())).thenReturn(future);
        when(channel.queryByChaincode(any())).thenReturn(responses);

        AppUser appUser = new AppUser("", "", null, "", "", null);
        this.objUnderTest = new SDKInterfaceImpl(appUser) {};
    }

    /*@Test
    public void dispatchElectionOverCheckTest() throws NetworkException, NetworkConfigException {
        this.objUnderTest.isElectionOver();
    }

    @Test(expected = NetworkException.class)
    public void dispatchElectionOverCheckProposalExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any())).thenThrow(ProposalException.class);
        this.objUnderTest.dispatchElectionOverCheck();
    }

    @Test(expected = NetworkConfigException.class)
    public void dispatchElectionOverCheckInvalidArgumentExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any()))
                .thenThrow(org.hyperledger.fabric.sdk.exception.InvalidArgumentException.class);
        this.objUnderTest.dispatchElectionOverCheck();
    }*/

    @Test
    public void getElectionDataTest() throws NetworkException, NetworkConfigException, IOException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException {
        byte[] answer = Files.readAllBytes(Paths.get("src/test/resources/electionDataExample.json"));
        when(responses.get(0).getChaincodeActionResponsePayload()).thenReturn(answer);
        ElectionDataIF electionData = this.objUnderTest.getElectionData();
        //TODO: JSON Assert
    }

    @Test(expected = NetworkException.class)
    public void getElectionDataProposalExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any())).thenThrow(ProposalException.class);
        this.objUnderTest.getElectionData();
    }

    @Test(expected = NetworkConfigException.class)
    public void getElectionDataInvalidArgumentExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any()))
                .thenThrow(org.hyperledger.fabric.sdk.exception.InvalidArgumentException.class);
        this.objUnderTest.getElectionData();
    }

    @Test
    public void getAllVotesTest() throws NetworkException, NetworkConfigException, IOException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException {
        String input = "[\"{\\\"candidate\\\":\\\"Test1\\\"}\",\"{\\\"candidate\\\":\\\"Test2\\\"}\"]";
        when(responses.get(0).getChaincodeActionResponsePayload()).thenReturn(input.getBytes());
        String[] votes = this.objUnderTest.getAllVotes();
        assertArrayEquals(votes, new String[]{"{\"candidate\":\"Test1\"}", "{\"candidate\":\"Test2\"}"});
    }

    @Test(expected = NetworkException.class)
    public void getAllVotesProposalExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any())).thenThrow(ProposalException.class);
        this.objUnderTest.getAllVotes();
    }

    @Test(expected = NetworkConfigException.class)
    public void getAllVotesInvalidArgumentExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any()))
                .thenThrow(org.hyperledger.fabric.sdk.exception.InvalidArgumentException.class);
        this.objUnderTest.getAllVotes();
    }

    @Test
    public void isElectionInitializedTest() throws NetworkException, NetworkConfigException, IOException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException {
        String input = "true";
        when(responses.get(0).getChaincodeActionResponsePayload()).thenReturn(input.getBytes());
        boolean res = this.objUnderTest.isElectionInitialized();
        assertTrue(res);
    }

    @Test(expected = NetworkException.class)
    public void isElectionInitializedProposalExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any())).thenThrow(ProposalException.class);
        this.objUnderTest.isElectionInitialized();
    }

    @Test(expected = NetworkConfigException.class)
    public void isElectionInitializedInvalidArgumentExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any()))
                .thenThrow(org.hyperledger.fabric.sdk.exception.InvalidArgumentException.class);
        this.objUnderTest.isElectionInitialized();
    }

    @Test
    public void isElectionOverTest() throws NetworkException, NetworkConfigException, IOException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException {
        String input = "ended";
        when(responses.get(0).getChaincodeActionResponsePayload()).thenReturn(input.getBytes());
        boolean res = this.objUnderTest.isElectionOver();
        assertTrue(res);
    }

    @Test(expected = NetworkException.class)
    public void isElectionOverProposalExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any())).thenThrow(ProposalException.class);
        this.objUnderTest.isElectionOver();
    }

    @Test(expected = NetworkConfigException.class)
    public void isElectionOverInvalidArgumentExceptionTest() throws NetworkException, NetworkConfigException,
            org.hyperledger.fabric.sdk.exception.InvalidArgumentException, ProposalException {
        when(this.channel.queryByChaincode(any()))
                .thenThrow(org.hyperledger.fabric.sdk.exception.InvalidArgumentException.class);
        this.objUnderTest.isElectionOver();
    }


}
