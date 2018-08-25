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

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import edu.kit.iti.formal.pse2018.evote.exceptions.AuthenticationException;
import edu.kit.iti.formal.pse2018.evote.exceptions.EnrollmentException;
import edu.kit.iti.formal.pse2018.evote.exceptions.InternalSDKException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkConfigException;
import edu.kit.iti.formal.pse2018.evote.exceptions.NetworkException;
import edu.kit.iti.formal.pse2018.evote.model.ElectionData;
import edu.kit.iti.formal.pse2018.evote.model.SDKEventListener;
import edu.kit.iti.formal.pse2018.evote.model.SDKInterface;
import edu.kit.iti.formal.pse2018.evote.model.sdkconnection.transactions.SingleStringQuery;
import edu.kit.iti.formal.pse2018.evote.utils.ElectionDataIF;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import javax.json.Json;
import javax.json.JsonReader;

import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import static org.mockito.Mockito.mock;

//THIS TEST ONLY WORKS WITH A RUNNING NETWORK
@Ignore
public class SDKConnectionITTest {

    private SupervisorSDKInterfaceImpl supervisorSDKInterface;
    private VoterSDKInterfaceImpl voterSDKInterface;
    private SDKEventListener supervisorEventListener;
    private SDKEventListener voterEventListener;
    private String userFilePath;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    public void setup() throws IOException, NetworkException, AuthenticationException, InternalSDKException, NetworkConfigException {
        //userFilePath = tempFolder.newFile("user1Ident").getAbsolutePath();
        userFilePath = "/tmp/evote/user1Ident";
        supervisorEventListener = mock(SDKEventListener.class);
        supervisorSDKInterface = SupervisorSDKInterfaceImpl.createInstance("admin", "adminpw", tempFolder.newFile("adminIdent").getAbsolutePath());
    }


    public void createUserTest() throws IOException, EnrollmentException {
        supervisorSDKInterface.createUser("testuser1", userFilePath);
    }


    public void createElectionTest() throws FileNotFoundException, NetworkException, NetworkConfigException {
        JsonReader reader = Json.createReader(new FileInputStream("src/test/resources/electionDataExample.json"));
        ElectionDataIF electionData = ElectionData.fromJSon(reader.readObject());
        supervisorSDKInterface.createElection(electionData);
    }

    public void queryElectionData(SDKInterface iface) throws NetworkException, NetworkConfigException {
        ElectionDataIF electionData = iface.getElectionData();
        System.out.println(electionData.getName());
        System.out.println(electionData.getDescription());
        System.out.println(Arrays.toString(electionData.getCandidates()));
        System.out.println(Arrays.toString(electionData.getCandidateDescriptions()));
        System.out.println(electionData.getVotingSystem().name());
        System.out.println(electionData.getStartDate().toString());
        System.out.println(electionData.getEndDate().toString());
        System.out.println(electionData.getEndCondition());
        System.out.println(electionData.getVoterCount());

    }

    public void queryCidTestQuery() throws NetworkException, ProposalException, InvalidArgumentException {
        SingleStringQuery query = new SingleStringQuery(supervisorSDKInterface.hfClient) {
            @Override
            protected String[] buildArgumentStrings() {
                return new String[0];
            }

            @Override
            protected String getFunctionName() {
                return "cidtest";
            }
        };
        query.query();
        System.out.println(query.getResult());
    }

    @Test
    public void all() throws NetworkConfigException, NetworkException, AuthenticationException, InternalSDKException, IOException, EnrollmentException, InvalidArgumentException, ProposalException {
        setup();
        //createUserTest();
        //createElectionTest();

        voterEventListener = mock(SDKEventListener.class);
        voterSDKInterface = new VoterSDKInterfaceImpl(userFilePath);
        queryElectionData(voterSDKInterface);
        //System.out.println("\u001B[33m" + "DISPATCH DONE" + "\u001B[0m");
        //verify(voterEventListener, times(1)).onElectionRunning();
        //voterSDKInterface.vote("{\"candidate\": \"hans\"}");
        System.out.println(voterSDKInterface.getOwnVote());


        System.out.println(Arrays.toString(supervisorSDKInterface.getAllVotes()));
        //queryCidTestQuery();
    }

}
